package com.example.LuddeShop;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1001, "Barkus Krunegård", "En hipp jacka för alla coola jyckar där ute!", 1050, "Hundjackor"));
        products.add(new Product(1002, "Floating Floof", "Flyter din hund? Ta det säkra före det osäkra och klicka hem denna exklusiva och snygga flytväst.", 589, "Hundjackor"));
        products.add(new Product(1003, "Sausage Dog Ultra", "Det perfekta plagget för dom lite kyligare promenaderna.", 2200, "Hundjackor"));
        products.add(new Product(1004, "Sir Barkington III", "Smoking för finare tillställningar.", 850, "Hundjackor"));
        products.add(new Product(1005, "Air Puppies", "En outfit är ingenting utan ett par schyssta kicks! Säkra looken med dessa stiliga skorna.", 89, "Hundskor"));
        products.add(new Product(1006, "Atomic Mongrel", "Med dessa löparskor på tassarna kommer din hund vara snabbast i kvarteret.", 150, "Hundskor"));
        products.add(new Product(1007, "Cozy-Pup", "Härliga strumpor i ullfrotté för regniga och kalla dagar när det är skönare att stanna inne framför brasan.", 975, "Hundskor"));
        products.add(new Product(1008, "Furry Potatoe Satchel", "Om du har en lat hund är denna väska ett måste! Stor nog för både hund och leksaker.", 1500, "Hundväska"));
        products.add(new Product(1009, "Bark Bag", "Modemedveten hundväska med fack för väsentligheter så som godis och leksaker.", 3000, "Hundväska"));
        products.add(new Product(1010, "Brandy Barrel", "Brandy barrel i klassisk stil - perfekt när törsten smyger sig på under promenaden.", 970, "Hundväska"));
    }

    //get product
    public Product getProduct(Integer id) {
        for (Product p : products) {
            if (p.getProductId() == id) {
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
        Product lastProduct = products.get(products.size() - 1);
        product.setProductId(lastProduct.getProductId() + 1);
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
