package shoesshop.model.DatabaseModels;

import java.util.*;

public class Customer{

    private final int id;
    private final Map<Integer, Order> orderMap;
    private final Map<Integer, Review> reviewMap;
    private CustomerLocation customerLocation;
    private String name, personal, username, password;
    
    public Customer(int id, String name, String personal, String username, String password) {
        this.orderMap = new HashMap<>();
        this.reviewMap = new HashMap<>();
        this.id = id;
        this.name = name;
        this.personal = personal;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public Map<Integer, Order> getOrders() {
        return orderMap;
    }

    public void addOrder(Order order) {
        orderMap.put(order.getId(), order);
    }

    public void removeOrder(Order order) {
        orderMap.remove(order.getId());
    }

    public Map<Integer, Review> getReviewsMap() {
        return reviewMap;
    }
    
    public void addReview(Review review) {
        reviewMap.put(review.getId(), review);
    }

    public void removeReview(Review review) {
        reviewMap.remove(review.getId());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public CustomerLocation getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(CustomerLocation customerLocation) {
        this.customerLocation = customerLocation;
    }
}
