package shoesshop;

import java.util.*;

public class Order {

    private final int id;
    private final Customer customer;
    private final Date date;
    private final boolean expedited;
    private Map<Integer, Product> productAssignment;

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

    public Customer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return date;
    }

    public boolean isExpedited() {
        return expedited;
    }
}
