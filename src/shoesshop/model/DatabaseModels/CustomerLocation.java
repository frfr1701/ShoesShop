package shoesshop.model.DatabaseModels;

public class CustomerLocation {

    private final int id;
    private String address;

    public CustomerLocation(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
