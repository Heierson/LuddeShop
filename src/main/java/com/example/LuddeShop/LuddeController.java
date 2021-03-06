package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LuddeController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String getToHomePage() {
        return "index";
    }

    @GetMapping("/login")
    String firstLogin() {
        return "login";
    }

    @PostMapping("/login")
    String login(HttpSession session, Model model, @RequestParam String userName, @RequestParam String password) {
        if (userName.equals("admin") & password.equals("dogs")) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("userName", userName);
        }

        if (session.getAttribute("userName") != null) {
            model.addAttribute("products", productService.getAllProducts());
            return "redirect:/warehouse";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/delete/{id}")
    String deleteProduct(HttpSession session, Model model, @PathVariable Long id) {
        if (session.getAttribute("userName") != null) {
            productService.deleteProductFromRepository(id);
            return "redirect:/warehouse";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/add")
    String showAddPage(HttpSession session, Model model) {
        if (session.getAttribute("userName") != null) {
            //need to add this empty object to the model in order for the addProduct template to function
            Product product = new Product();
            model.addAttribute("product", product);
            return "addProduct";
        }

        return "redirect:/login";
    }

    @PostMapping("/admin/add")
    String addProduct(HttpSession session, Model model, @Valid Product product, BindingResult result) {
        if (session.getAttribute("userName") != null) {
            if (result.hasErrors()) {
                return "addProduct";
            }
            productService.addProductToRepository(product);
            model.addAttribute("products", productService.getAllProducts());
            return "redirect:/warehouse";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/edit/{id}")
    String edit(Model model, @PathVariable Long id){
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "addProduct";
    }

    @GetMapping("/warehouse")
    String warehouse(Model model, HttpSession session){
        if (session.getAttribute("userName") != null) {
            List<Product> products = (List<Product>) productService.getAllProducts();
            model.addAttribute("products", products);
            return "warehouse";
        }
      return "redirect:/login";
    }




    @GetMapping("/logout")
    String logout(HttpSession session) {
        return "logout";
    }

    @GetMapping("/allProducts")
    public String products(Model model) {
        List<Product> products = (List<Product>) productService.getAllProducts();
        model.addAttribute("products", products);
        return "allProducts";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        List<Product> cart = (List) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return "cart";
    }

    @GetMapping("/addproduct")
    public String addProduct(HttpSession session, @RequestParam Long id) {

        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        Product product = productService.getProduct(id);
        boolean notInCart = true;
        for (Product p : cart) {
            if (p.getProductId() == id){
                notInCart =false;
        }
        }
        if (notInCart){
            cart.add(product);
            getSum(session, cart);
        }
        return "redirect:/allProducts";
    }

    @GetMapping("/removeproduct")
    public String removeProduct(HttpSession session, @RequestParam Long id) {
        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        Product productToRemove = null;
        for (Product product : cart) {
            if (product.getProductId() == id){
                productToRemove = product;
               break;
            }
        }
        if (productToRemove != null){
            cart.remove(productToRemove);
        }
        getSum(session, cart);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkOut(HttpSession session) {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkedOut(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        for (Product product : cart) {
            productService.deleteProductFromRepository(product.getProductId());
        }
        session.removeAttribute("cart");
        session.removeAttribute("sum");
        return "redirect:/allProducts";
    }

    public static void getSum(HttpSession session, List<Product> cart) {
        Integer sum = ProductService.sum(cart);
        session.setAttribute("sum", sum);
    }

    @GetMapping("/productDetails")
    public String viewProductDetails(Model model, @RequestParam Long id) {
        Product productToView = productService.getProduct(id);
        model.addAttribute("product", productToView);
        return "productDetails";
    }

    @GetMapping("/addproductdetail")
    public String addProductDetail(HttpSession session, @RequestParam Long id) {

        @SuppressWarnings("unchecked")
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        Product product = productService.getProduct(id);
        cart.add(product);
        getSum(session, cart);
        return "redirect:/cart";
    }


}
