package shoesshop.repository;

import shoesshop.model.DatabaseModels.*;
import shoesshop.properties.PropertiesReader;
import static java.sql.ResultSet.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

public class Repository {

    PropertiesReader pr;
    int rowChanged;
    String query;
    ResultSet rs;

    public Repository() {
        this.pr = new PropertiesReader();
        pr.loadProperties();
    }

    private String OneOrAll(String input, String one, String all) {
        return input.length() > 0 ? one : all;
    }

    public Customer logIn(String username, String password) {
        Customer customer = null;
        query = "SELECT * FROM customer WHERE username = ? AND password = ?";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getInt("ID"), rs.getString("Name"), rs.getString("Personal"), rs.getString("Username"), rs.getString("Password"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return customer;
    }

    public Map<Integer, Customer> getCustomerMap(String input) {
        Map<Integer, Customer> customers = new HashMap<>();

        query = OneOrAll(input,
                "SELECT * FROM customer WHERE name = ?",
                "SELECT * FROM customer");

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

    public Map<Integer, Shoe> getShoesMap(String input) {
        Map<Integer, Shoe> shoes = new HashMap<>();

        query = OneOrAll(input,
                "SELECT * FROM shoes WHERE name = ?",
                "SELECT * FROM shoes");

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (input.length() > 0) {
                stmt.setString(1, input);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                shoes.put(rs.getInt("id"), new Shoe(rs.getInt("id"), rs.getString("name"), rs.getFloat("price")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return shoes;
    }

    public void mapOrdersToCustomers(Map<Integer, Customer> customers) {

        String input = customers.values().stream().map(Customer::getId).map(s -> String.valueOf(s)).collect(Collectors.joining(","));
        query = "Select Customer_ID as CustomerID, "
                + "skodatabas.order.id as OrderID, "
                + "skodatabas.order.Expedited as Expedited, "
                + "skodatabas.order.date as Date from skodatabas.order "
                + "inner join customer on customer.id = skodatabas.order.Customer_ID "
                + "where skodatabas.order.Customer_ID in(?);";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (input.length() > 0) {
                stmt.setString(1, input);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                customers.get(rs.getInt("customerID")).addOrder(new Order(rs.getInt("OrderID"), rs.getDate("date"), (rs.getInt("Expedited")==1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }
    public void mapProductsToOrders(Map<Integer, Customer> customers) {
        Map<Integer, Order> Orders = customers.keySet().stream()
                .flatMap(t -> customers.get(t).getOrders().values().stream())
                .collect(Collectors.toMap(e->e.getId(), e->e));
        
        String input = Orders.values().stream().map(Order::getId).map(s -> String.valueOf(s)).collect(Collectors.joining(","));
        
        query = "Select Customer_ID as CustomerID, "
                + "skodatabas.order.id as OrderID, "
                + "skodatabas.order.Expedited as Expedited, "
                + "skodatabas.order.date as Date from skodatabas.order "
                + "inner join customer on customer.id = skodatabas.order.Customer_ID "
                + "where skodatabas.order.Customer_ID in(?);";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (input.length() > 0) {
                stmt.setString(1, input);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                customers.get(rs.getInt("customerID")).addOrder(new Order(rs.getInt("OrderID"), rs.getDate("date"), (rs.getInt("Expedited")==1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }
}
