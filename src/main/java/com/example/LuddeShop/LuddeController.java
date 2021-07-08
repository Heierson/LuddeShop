package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LuddeController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin/delete/{id}")
    String deleteProduct(Model model, @PathVariable Integer id) {
        productService.deleteProductFromRepository(id);
        return "allProducts";
    }

    @GetMapping("/admin/add")
    String showAddPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @PostMapping("/admin/add")
    String addProduct(Model model, @ModelAttribute Product product) {
        productService.addProductToRepository(product);
        model.addAttribute(product);
        return "redirect:/allProducts";
    }

    @GetMapping("/allProducts")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "allProducts";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session) {
        List<Product> cart = (List)session.getAttribute("cart");
        return "cart";
    }

    @GetMapping("/addproduct")
    public String addProduct(HttpSession session, @RequestParam int id) {

        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        Product product = productService.getProduct(id);
        cart.add(product);
        session.setAttribute(product.getProductName(), 1);
        getSum(session, cart);
        return "redirect:/allProducts";
    }

    @GetMapping("/removeproduct")
    public String removeProduct(HttpSession session, @RequestParam int id) {
        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        Product productToRemove = productService.getProduct(id);
        cart.remove(productToRemove);
        getSum(session, cart);
        return "redirect:/cart";
    }

    public static void getSum(HttpSession session, List<Product> cart) {
        double sum = ProductService.sum(cart);
        session.setAttribute("sum", sum);
    }

}
