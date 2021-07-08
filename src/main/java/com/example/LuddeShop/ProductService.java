package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    Product addProductToRepository(Product p){
        productRepository.addProduct(p);
        return p;
    }

    void deleteProductFromRepository(Integer id){
        productRepository.deleteProduct(id);
    }
}
