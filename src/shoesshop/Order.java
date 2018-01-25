package shoesshop;

import java.util.*;

public class Order {

    private final int id;
    private final Customer customer;
    private final Date date;
    private Map<Integer, Product> productsInOrder;
    private boolean expedited;

    public Order(int id, Customer customer, Date date, boolean expedited) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.expedited = expedited;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Product> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(Map<Integer, Product> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }

    public void removeProductInOrder(Product product) {
        productsInOrder.remove(product.getId());
    }

    public void addProductInOrder(Product product) {
        productsInOrder.put(product.getId(), product);
    }

    public Customer getCustomer() {
        return customer;
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
