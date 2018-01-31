package shoesshop.model.DatabaseModels;

import java.util.*;

public class Color {

    private final int id;
    private final String name;
    private final Map<Integer, Product> productMap;

    public Color(int id, String name) {
        this.productMap = new HashMap<>();
        this.id = id;
        this.name = name;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
