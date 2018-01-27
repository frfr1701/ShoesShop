package shoesshop;

import java.util.*;

public class Order {

    private final int id;
    private final Date date;
    private Customer customer;
    private boolean expedited;
    private Map<Integer, Product> productAssignment;

    public Order(int id, Date date, boolean expedited) {
        this.id = id;
        this.date = date;
        this.expedited = expedited;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Product> getProductsInOrder() {
        return productAssignment;
    }

    public void setProductsInOrder(Map<Integer, Product> productsInOrder) {
        this.productAssignment = productsInOrder;
    }

    public void removeProductInOrder(Product product) {
        productAssignment.remove(product.getId());
    }

    public void addProductInOrder(Product product) {
        productAssignment.put(product.getId(), product);
    }

    public Map<Integer, Product> getProductAssignment() {
        return productAssignment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
