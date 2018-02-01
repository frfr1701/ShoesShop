package shoesshop.model.DatabaseModels;

import java.util.*;

public class Brand{

    private final int id;
    private final String name;
    private final Map<Integer, Shoe> shoeMap;
    

    public Brand(int id, String name) {
        this.shoeMap = new HashMap<>();
        this.id = id;
        this.name = name;
    }

    public Map<Integer, Shoe> getShoeMap() {
        return shoeMap;
    }
    
    public void addShoe(Shoe shoe) {
        shoeMap.put(shoe.getId(), shoe);
    }

    public void removeShoe(Shoe shoe) {
        shoeMap.remove(shoe.getId());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
