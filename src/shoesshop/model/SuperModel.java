package shoesshop.model;

import shoesshop.model.DatabaseModels.*;
import shoesshop.controller.*;
import shoesshop.repository.*;
import java.util.*;

public class SuperModel {

    private final Repository repository;
    private final List<String> viewList;
    private Customer customer;
    private Map<Integer, Customer> customerMap;
    private Map<Integer, Shoe> shoes;
    
    public SuperModel(Repository repository) {
        this.customerMap = new HashMap<>();
        this.repository = repository;
        this.viewList = new ArrayList<>();
        this.shoes = new HashMap<>();
    }


    public List<String> getViewList() {
        return viewList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getId(), customer);
    }

    public void removeCustomer(Customer customer) {
        customerMap.remove(customer.getId());
    }

    public Map<Integer, Customer> getCustomerMap() {
        return customerMap;
    }

    public void setCustomerMap(Map<Integer, Customer> customerMap) {
        this.customerMap = customerMap;
    }
    
    
    public Customer getCustomer() {
        return customer;
    }

    public Map<Integer, Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(Map<Integer, Shoe> shoes) {
        this.shoes = shoes;
    }
    
    public void update(RepoInterface f) {
        f.update(this, repository);
    }

}
