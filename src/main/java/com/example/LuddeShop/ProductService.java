package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    Product addProductToRepository(Product p) {
        productRepository.addProduct(p);
        return p;
    }

    void deleteProductFromRepository(Integer id) {
        productRepository.deleteProduct(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    Product getProduct(int id) {
        return productRepository.getProduct(id);
    }

    static double sum(List<Product> products) {
        double sum = 0;
        if(!products.isEmpty()) {
            for (Product item : products) {
                sum += item.getPrice();
            }
        }
        return sum;
    }
}
