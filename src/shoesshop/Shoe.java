package shoesshop;

import java.util.*;

public class Shoe {

    private final int id;
    private final String name;
    private final Brand brand;
    private final float price;
    private final Map<Integer, Category> categoryAssignment;

    public Shoe(int id, Brand brand, String name, float price) {
        this.categoryAssignment = new HashMap<>();
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Category> getCategoryAssignment() {
        return categoryAssignment;
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

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
