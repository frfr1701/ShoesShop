package shoesshop.model.DatabaseModels;

import java.util.*;

public class Product {

    private final int id;
    private Stock stock;
    private final Map<Integer, OutOfStock> outOfStockMap;

    public Product(int id) {
        this.outOfStockMap = new HashMap<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, OutOfStock> getOutOfStockMap() {
        return outOfStockMap;
    }

    public void addOutOfStock(OutOfStock outOfStock) {
        outOfStockMap.put(outOfStock.getId(), outOfStock);
    }

    public void removeOutOfStock(OutOfStock outOfStock) {
        outOfStockMap.remove(outOfStock.getId());
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
