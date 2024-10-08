package com.springdemo.controller;

import com.springdemo.entity.Order;
import com.springdemo.entity.Product;
import com.springdemo.service.OrderService;
import com.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private  final OrderService orderService;
    private  final ProductService productService;

    String order="order";
    String orderForm="order-form";

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

        model.addAttribute(order, theOrder);
        return orderForm;
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@Valid @ModelAttribute("order") Order theOrder, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute(order, theOrder);
            return orderForm;
        }
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
        Order theOrder=orderService.getOrderByID(id);
        model.addAttribute(order, theOrder);
        return "order-products";
    }


    @GetMapping("/manage-products")
    public String editOrderProducts(@RequestParam("orderId") int orderId, Model model) {
        Order theorder=orderService.getOrderByID(orderId);
        List<Product> allProducts=productService.getProducts();
        model.addAttribute(order,theorder);
        model.addAttribute("allProducts",allProducts);
        return "view-order-products";
    }



    @PostMapping("/saveProducts")
    public String saveOrderProducts(@RequestParam("orderId") int orderId,
                                 @RequestParam(value = "productIds", required = false) List<Integer> productIds) {
        Order theOrder = orderService.getOrderByID(orderId);


        List<Product> selectedProducts = productService.getProductsByIds(productIds);
        theOrder.setProducts(selectedProducts);

        orderService.saveOrder(theOrder);
        return "redirect:/order/list";
    }

}
