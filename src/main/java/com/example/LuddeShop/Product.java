package com.example.LuddeShop;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="PRODUCTID")
    private Long productId;

    @Column (name="PRODUCTNAME")
    @Size(max=64)
    @NotEmpty
    private String productName;

    @Column (name="PRODUCTDESCRIPTION")
    @Size(max=512)
    @NotEmpty
    private String productDescription;

    @Column (name="PRODUCTPRICE")
    @Positive
    @NotNull
    private Integer ProductPrice;

    @Column (name="PRODUCTCATEGORY")
    @Size(max=64)
    @NotEmpty
    private String productCategory;

    public Product(){
    }

    public Product(String productName, String productDescription, Integer ProductPrice, String productCategory) {
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

    public Integer getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.ProductPrice = productPrice;
    }
}
