package shoesshop;

import java.sql.*;
import static java.sql.ResultSet.*;
import java.util.*;

public class Repository {

    public Map<Integer, Customer> customers;
    Map<Integer, Shoe> shoes;

    public void loadCustomers() {
        customers = new HashMap<>();
        
        PropertiesReader pr = new PropertiesReader();
        pr.loadProperties();

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                Statement stmt = (Statement) con.createStatement(TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
            
            while (rs.next()) {
                customers.put(rs.getInt("id"), new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("personal"), "ab", "ab"));
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }
    
}
