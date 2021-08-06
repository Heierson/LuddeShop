package com.example.LuddeShop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LuddeShopApplicationTests {

    @Autowired
    ProductService productService;

	@Test
	void contextLoads() {
	}

	@Test
    public void testProduct(){
	    Product product = productService.getAllProducts().get(0);
	    Assertions.assertEquals("Barkus Krunegård", product.getProductName());
    }

}
