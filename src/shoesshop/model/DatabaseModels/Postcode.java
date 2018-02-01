package shoesshop.model.DatabaseModels;

import java.util.*;

public class Postcode {

    private final int id;
    private final String nummer;
    private final Map<Integer, CustomerLocation> customerLocationMap;
    

    public Postcode(int id, String nummer) {
        this.customerLocationMap = new HashMap<>();
        this.id = id;
        this.nummer = nummer;
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

    public String getNummer() {
        return nummer;
    }
}
