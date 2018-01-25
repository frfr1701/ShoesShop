
package shoesshop;

public class CustomerLocation {
    private String Address, City, Postcode;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getPostcode() {
        return Postcode;
    }

    public CustomerLocation(String Address, String City, String Postcode) {
        this.Address = Address;
        this.City = City;
        this.Postcode = Postcode;
    }

    public void setPostcode(String Postcode) {
        this.Postcode = Postcode;
    }
    
}
