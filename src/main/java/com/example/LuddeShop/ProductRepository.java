package com.example.LuddeShop;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();   //todo lägga in en kategorivariabel för att kunna sortera utbudet?
        //todo ska vi lägga in produktbeskrivningarna direkt i HTML? Kanske inte användbar som kod eftersom den är svår att söka och använda?
        products.add(1001, "Barkus Krunegård", "En hipp jacka för alla coola jyckar där ute!", 1050);
        products.add(1002, "Floating Floof", "Flytväst", 589);
        products.add(1003, "Sausage Ultra", "Dunjacka", 2200);
        products.add(1004, "Sir Barkington III", "Smoking för finare tillställningar", 850);
        products.add(1005, "Air Puppies", "Street skor för hund", 89);
        products.add(1006, "Atomic Mongrel", "Med dessa löparskor på tassarna kommer din hund vara snabbast i kvarteret.", 150);
        products.add(1007, "Cozy-Pup", "Hundsstrumpor", 975);
        products.add(1008, "Furry Potatoe Satchel", "Modemedveten hundväska", 1500);
        products.add(1009, "Bark Bark", "Hundväska", 3000);
        products.add(1010, "Brandy Barrel", "Brandy barrel i klassisk stil - perfekt när törsten smyger sig på under promenaden.", 970);
    }

    //get product
    public getProduct(Integer id) {
        for (Product p : products) {
            if (p.getProductId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    //get all products
    public List<Product> getProducts() {
        return products;
    }

    //add product
    public Product addProduct(Product product) {
        Product lastProduct = products.get(products.size()-1);
        product.setProductId(lastProduct.getProductId()+1);
        products.add(product);
        return product;
    }

    //delete product
    public void deleteProduct(Integer id) {
        Product productToDelete = this.getProduct(id);
        if (productToDelete != null) {
            products.remove(productToDelete);
        }
    }
}
