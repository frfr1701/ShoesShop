package shoesshop.model.DatabaseModels;

import java.util.*;

public class Shoe {

    private final int id;
    private String name;
    private float price;
    private final Map<Integer, Category> categoryMap;
    private final Map<Integer, Review> reviewMap;
    

    public Shoe(int id, String name, float price) {
        this.reviewMap = new HashMap<>();
        this.categoryMap = new HashMap<>();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Category> getCategoryAssignment() {
        return categoryMap;
    }

    public void addCategory(Category category) {
        categoryMap.put(category.getId(), category);
    }

    public void removeCategory(Category category) {
        categoryMap.remove(category.getId());
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
