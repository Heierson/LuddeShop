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

    @GetMapping("/login")
    String firstLogin(){
        return "login";
    }

    @PostMapping("/login")
    String login(HttpSession session, Model model, @RequestParam String userName, @RequestParam String password){
        if(userName.equals("admin")&password.equals("dogs")){
            session.setAttribute("loggedIn", true);
            session.setAttribute("userName", userName);
        }

        if(session.getAttribute("userName") != null){
            model.addAttribute("products", productService.getAllProducts());
            return "allProducts";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/delete/{id}")
    String deleteProduct(HttpSession session,Model model, @PathVariable Integer id) {
        if(session.getAttribute("userName") != null) {
            productService.deleteProductFromRepository(id);
            return "redirect:/allProducts";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/add")
    String showAddPage(HttpSession session,Model model) {
        if(session.getAttribute("userName") != null) {
            //need to add this empty object to the model in order for the addProduct template to function
            Product product = new Product();
            model.addAttribute("product", product);
            return "addProduct";
        }
        return "redirect:/login";
    }

    @PostMapping("/admin/add")
    String addProduct(HttpSession session,Model model, @ModelAttribute Product product) {
        if(session.getAttribute("userName") != null) {
            productService.addProductToRepository(product);
            return "redirect:/allProducts";
        }
        return "redirect:/login";
    }

    @GetMapping("/")
    String index(){
        return "index";
    }

    @GetMapping("/logout")
    String logout(HttpSession session){
            return "logout";
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

    @GetMapping("/productDetails")
    public String viewProductDetails(Model model, @RequestParam int id) {
        Product productToView = productService.getProduct(id);
        model.addAttribute("product", productToView);
        return "productDetails";
    }


}
