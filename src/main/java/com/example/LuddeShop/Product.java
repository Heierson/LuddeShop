package com.example.LuddeShop;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="PRODUCTID")
    private Long productId;
    @Column (name="PRODUCTNAME")
    private String productName;
    @Column (name="PRODUCTDESCRIPTION")
    private String productDescription;
    @Column (name="PRODUCTPRICE")
    private double ProductPrice;
    @Column (name="PRODUCTCATEGORY")
    private String productCategory;

    public Product(){
    }

    public Product(String productName, String productDescription, double ProductPrice, String productCategory) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.ProductPrice = ProductPrice;
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        this.ProductPrice = productPrice;
    }
}
