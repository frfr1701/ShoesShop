package shoesshop;

public class CustomerLocation {

    private final int id;
    private final String address;
    private final City city;
    private final Postcode postcode;

    public CustomerLocation(int id, String address, City city, Postcode postcode) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }
    
    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public Postcode getPostcode() {
        return postcode;
    }
}
