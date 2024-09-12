package com.springdemo.controller;

import com.springdemo.entity.Product;
import com.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public  String listProducts(Model theModel){
        //get products from dao
        List<Product> theProducts=productService.getProducts();
        //add the products to the model
        theModel.addAttribute("products",theProducts);
        return "list-products";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Product theProduct=new Product();
        model.addAttribute("product", theProduct);
        return "product-form";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product theProduct, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", theProduct);
            return "product-form";
        }
        // save the customer using our service
        productService.saveProduct(theProduct);
        return "redirect:/product/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int theId,Model theModel){
        Product theProduct=productService.getProduct(theId);

        theModel.addAttribute("product",theProduct);

        return "product-form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int theId){
        productService.deleteProduct(theId);

        return "redirect:/product/list";
    }

}
