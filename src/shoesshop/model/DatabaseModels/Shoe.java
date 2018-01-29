package shoesshop.model.DatabaseModels;

import shoesshop.model.DatabaseModels.Brand;
import shoesshop.model.DatabaseModels.Category;
import java.util.*;

public class Shoe {

    private final int id;
    private Brand brand;
    private String name;
    private float price;
    private Map<Integer, Category> categoryAssignment;

    public Shoe(int id, String name, float price) {
        this.categoryAssignment = new HashMap<>();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Category> getCategoryAssignment() {
        return categoryAssignment;
    }

    public void setCategoryAssignment(Map<Integer, Category> categoryAssignment) {
        this.categoryAssignment = categoryAssignment;
    }
    
    public void addCategory(Category category) {
        categoryAssignment.put(category.getId(), category);
    }

    public void removeCategory(Category category) {
        categoryAssignment.remove(category.getId());
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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
