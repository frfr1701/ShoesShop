package shoesshop;

import java.util.*;

public class Customer {

    private final int id;
    private String name, personal, username, password;
    CustomerLocation customerLocation;
    Map<Integer, Order> orders;

    public Customer(int id, String name, String personal, String username, String password, CustomerLocation customerLocation) {
        this.id = id;
        this.orders = new HashMap<>();
        this.name = name;
        this.personal = personal;
        this.username = username;
        this.password = password;
        this.customerLocation = customerLocation;
    }

    public int getId() {
        return id;
    }
    
    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void removeOrder(Order order) {
        orders.remove(order.getId());
    }

    public Map<Integer, Order> getOrders() {
        return orders;
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
