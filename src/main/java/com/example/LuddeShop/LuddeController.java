package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

        return "allProducts";
    }

    @GetMapping("/removeproduct")
    public String removeProduct(HttpSession session, @RequestParam int id) {
        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        Product productToRemove = productService.getProduct(id);
        cart.remove(productToRemove);
        return "allProducts";
    }

    @GetMapping("/productDetails/{id}")
    public String viewProductDetails(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProduct(id));
        return "productDetails";
    }

}
