package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LuddeController {

    @Autowired
    private ProductRepository repo;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("productlist", repo);
        return "allProducts";
    }

    @GetMapping("/addproduct")
    public String addProduct(HttpSession session, @RequestParam int id) {

        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>)session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        Product product = repo.getProductById(id);
        cart.add(product);

        return "allProducts";
    }

    @GetMapping("/removeproduct")
    public String removeProduct(HttpSession session, @RequestParam int id) {
        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>)session.getAttribute("cart");
        Product productToRemove = repo.getProductById(id);
        cart.remove(productToRemove);
        return "cart";
    }

}
