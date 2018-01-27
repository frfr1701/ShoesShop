package shoesshop;

public class CustomerLocation {

    private final int id;
    private final Customer customer;
    private String address;
    private City city;
    private Postcode postcode;

    public CustomerLocation(int id, Customer customer, String address, City city, Postcode postcode) {
        this.id = id;
        this.customer = customer;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
    }
    
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Postcode getPostcode() {
        return postcode;
    }

    public void setPostcode(Postcode postcode) {
        this.postcode = postcode;
    }
}
