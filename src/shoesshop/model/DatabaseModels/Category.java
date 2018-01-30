package shoesshop.model.DatabaseModels;

import java.util.*;

public class Category{

    private final int id;
    private final String name;
    private final Map<Integer, Shoe> shoeMap;

    public Category(int Id, String name) {
        shoeMap = new HashMap<>();
        this.id = Id;
        this.name = name;
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
