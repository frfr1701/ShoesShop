package shoesshop.model.DatabaseModels;

import java.util.*;

public class Size {

    private final int id;
    private final int size;
    private final Map<Integer, Product> productMap;

    public Size(int id, int size) {
        this.productMap = new HashMap<>();
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
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

    public int getSize() {
        return size;
    }
}
