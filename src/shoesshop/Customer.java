package shoesshop;

public class Customer {

    private final int id;
    private CustomerLocation customerLocation;
    private String name, personal, username, password;

    public Customer(int id, String name, String personal, String username, String password) {
        this.id = id;
        this.name = name;
        this.personal = personal;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public CustomerLocation getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(CustomerLocation customerLocation) {
        this.customerLocation = customerLocation;
    }
}
