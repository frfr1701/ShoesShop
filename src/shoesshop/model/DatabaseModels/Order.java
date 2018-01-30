package shoesshop.model.DatabaseModels;

import java.util.*;

public class Order {

    private final int id;
    private final java.sql.Date date;
    private final List<Product> productList;
    private boolean expedited;

    public Order(int id, java.sql.Date date, boolean expedited) {
        this.productList = new ArrayList<>();
        this.expedited = expedited;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void removeProductList(Product product) {
        productList.remove(product);
    }

    public void addProductList(Product product) {
        productList.add(product);
    }

    public Date getDate() {
        return date;
    }

    public boolean isExpedited() {
        return expedited;
    }

    public void setExpedited(boolean expedited) {
        this.expedited = expedited;
    }
}
