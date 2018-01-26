package shoesshop;

public class Category {
    
    private final int id;
    private final String name;

    public Category(int Id, String name) {
        this.id = Id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
