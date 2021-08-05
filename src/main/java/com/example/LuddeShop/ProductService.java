package com.example.LuddeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepos productRepository;

    Product addProductToRepository(Product p) {
        productRepository.save(p);
        return p;
    }

    void deleteProductFromRepository(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>)productRepository.findAll();
        return products;
    }

    //What if we looking for something that doesn't exist?
    Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    static Integer sum(List<Product> products) {
        Integer sum = 0;
        if(!products.isEmpty()) {
            for (Product item : products) {
                sum  += item.getProductPrice();
            }
        }
        return sum;
    }
}
