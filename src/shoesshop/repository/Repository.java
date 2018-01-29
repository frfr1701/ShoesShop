package shoesshop.repository;

import shoesshop.model.DatabaseModels.Customer;
import shoesshop.properties.PropertiesReader;
import static java.sql.ResultSet.*;
import java.sql.*;
import java.util.*;

public class Repository {

    PropertiesReader pr;
    int rowChanged;
    String query;
    ResultSet rs;

    public Repository() {
        this.pr = new PropertiesReader();
        pr.loadProperties();
    }

    public Map<Integer, Customer> getCustomerMap(String input) {
        Map<Integer, Customer> customers = new HashMap<>();

        if (input.length() > 0) {
            query = "SELECT * FROM customer WHERE name = ?";
        } else {
            query = "SELECT * FROM customer";
        }

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (input.length() > 0) {
                stmt.setString(1, input);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                customers.put(rs.getInt("id"), new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("personal"), "ab", "ab"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return customers;
    }
}
