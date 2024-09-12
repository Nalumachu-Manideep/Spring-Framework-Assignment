package com.springdemo.controller;

import com.springdemo.dao.OrderDAO;
import com.springdemo.entity.Order;
import com.springdemo.entity.Product;
import com.springdemo.service.OrderService;
import com.springdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private  final OrderService orderService;
    private  final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }



    @GetMapping("/list")
    public String listOrders(Model theModel){
        List<Order> theOrders=orderService.getOrders();

        theModel.addAttribute("orders",theOrders);

        return "list-orders";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Order theOrder=new Order();

        model.addAttribute("order", theOrder);
        return "order-form";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@Valid @ModelAttribute("order") Order theOrder, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Add the product back to the model to retain form data on error
            model.addAttribute("order", theOrder);
            // Return the form with validation errors displayed
            return "order-form";
        }
        // save the customer using our service
        orderService.saveOrder(theOrder);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("orderId") int theId){
        orderService.deleteOrder(theId);

        return "redirect:/order/list";
    }

    @GetMapping("/products")
    public String showOrderProducts(@RequestParam("orderId") int id, Model model) {

        Order order=orderService.getOrderByID(id);
        model.addAttribute("order", order);
        return "order-products";
    }


    @GetMapping("/manage-products")
    public String editOrderProducts(@RequestParam("orderId") int orderId, Model model) {
        Order theorder=orderService.getOrderByID(orderId);
        List<Product> allProducts=productService.getProducts();
        // Add the products to the model
        model.addAttribute("order",theorder);
        model.addAttribute("allProducts",allProducts);
        // Return the JSP view
        return "view-order-products";
    }



    @PostMapping("/saveProducts")
    public String saveOrderProducts(@RequestParam("orderId") int orderId,
                                 @RequestParam(value = "productIds", required = false) List<Integer> productIds) {
        Order order = orderService.getOrderByID(orderId);
        System.out.println("Received orderId: " + orderId);
        System.out.println("Received productIds: " + productIds);

        List<Product> selectedProducts = productService.getProductsByIds(productIds);
        order.setProducts(selectedProducts);

        orderService.saveOrder(order);
        return "redirect:/order/list";
    }

}
