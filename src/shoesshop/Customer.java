package shoesshop;

import java.util.*;

public class Customer {

    private final int id;
    private final CustomerLocation customerLocation;
    private final String name, personal, username, password;
    private final Map<Integer, Order> orders;

    public Customer(int id, String name, String personal, String username, String password, CustomerLocation customerLocation) {
        this.orders = new HashMap<>();
        this.id = id;
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

    public String getPersonal() {
        return personal;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public CustomerLocation getCustomerLocation() {
        return customerLocation;
    }
}
