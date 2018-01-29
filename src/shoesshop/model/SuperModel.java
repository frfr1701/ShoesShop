package shoesshop.model;

import shoesshop.model.DatabaseModels.*;
import shoesshop.controller.*;
import shoesshop.repository.*;
import java.util.*;

public class SuperModel {

    private final Repository repository;
    private Map<Integer, Customer> customers;
    private Map<Integer, Shoe> shoes;

    public SuperModel(Repository repository) {
        this.repository = repository;
        customers = new HashMap<>();
        shoes = new HashMap<>();
    }

    public void setCustomers(Map<Integer, Customer> customers) {
        this.customers = customers;
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public void update(RepoInterface f) {
        f.update(this, repository);
    }

}
