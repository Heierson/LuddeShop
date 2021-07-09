package com.example.LuddeShop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class LuddeShopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddProduct() {
		ProductRepository productRepository = new ProductRepository();
		productRepository.addProduct(new Product(1099,"test","desc",22.22,"test"));
		int size = productRepository.getProducts().size();
		Assertions.assertEquals(11, size );
	}

	@Test
	public void testSum(){
		ProductRepository productRepository = new ProductRepository();
		List<Product> products = productRepository.getProducts();
		double expected = 11373;
		double result = ProductService.sum(products);
		Assertions.assertEquals(expected, result);
	}

}
