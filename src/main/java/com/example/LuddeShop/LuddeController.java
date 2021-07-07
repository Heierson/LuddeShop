package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LuddeController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin/delete/{id}")
    String deleteProduct(Model model, @PathVariable Integer id){
        productService.deleteProductFromRepository(id);
        return "allProducts";
    }

    @GetMapping("/admin/add")
    String showAddPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @PostMapping("/admin/add")
    String addProduct(Model model, @ModelAttribute Product product){
        productService.addProductToRepository(product);
        model.addAttribute(product);
        return "allProducts";
    }
}
