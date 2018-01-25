
package shoesshop;

import java.util.*;

class Shoes {
    private final int id;
    private String brand, Color, name;
    private float price;
    List<String> categoryAssignment;
    

    public Shoes(int id, String brand, String Color, String name, float price) {
        this.id = id;
        this.brand = brand;
        this.Color = Color;
        this.name = name;
        this.price = price;
        categoryAssignment = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void addCategory(String category) {
        categoryAssignment.add(category);
    }

    public void removeCategory(String category) {
        categoryAssignment.remove(category);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
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
