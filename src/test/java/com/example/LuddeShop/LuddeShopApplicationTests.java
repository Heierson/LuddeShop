package com.example.LuddeShop;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class LuddeShopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddProduct() {
		ProductRepository productRepository = new ProductRepository();
		int size = productRepository.getProducts().size();
		productRepository.addProduct(new Product(1099,"test","desc",22.22,"test"));


	}

}
