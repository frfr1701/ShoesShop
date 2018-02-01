package shoesshop.model.DatabaseModels;

import java.util.*;

public class City{

    private final int id;
    private final String name;
    private final Map<Integer, CustomerLocation> customerLocationMap;
    
    public City(int id, String name) {
        this.customerLocationMap = new HashMap<>();
        this.id = id;
        this.name = name;
    }
    
    public void addCustomerLocation(CustomerLocation customerLocation) {
        customerLocationMap.put(customerLocation.getId(), customerLocation);
    }

    public void removeCustomerLocation(CustomerLocation customerLocation) {
        customerLocationMap.remove(customerLocation.getId());
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
