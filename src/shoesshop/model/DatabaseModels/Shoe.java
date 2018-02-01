package shoesshop.model.DatabaseModels;

import java.util.*;

public class Shoe {

    private final int id;
    private String name;
    private float price;
    private final Map<Integer, Review> reviewMap;
    private final Map<Integer, Product> productMap;
    

    public Shoe(int id, String name, float price) {
        this.productMap = new HashMap<>();
        this.reviewMap = new HashMap<>();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Review> getReviewsMap() {
        return reviewMap;
    }
    
    public void addReview(Review review) {
        reviewMap.put(review.getId(), review);
    }

    public void removeReview(Review review) {
        reviewMap.remove(review.getId());
    }
    public Map<Integer, Product> getProductMap() {
        return productMap;
    }
    
    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public void removeProduct(Product product) {
        productMap.remove(product.getId());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
