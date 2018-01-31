package shoesshop.repository;

import shoesshop.model.DatabaseModels.*;
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

    public Customer logIn(String username, String password, Map<Integer, Customer> customers) {
        Customer customer = null;
        query = "SELECT * FROM customer WHERE username = ? AND password = ?";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (!customers.containsKey(rs.getInt("ID"))) {
                    customer = new Customer(rs.getInt("ID"), rs.getString("Name"), rs.getString("Personal"), rs.getString("Username"), rs.getString("Password"));
                    customers.put(customer.getId(), customer);
                }
                customer = customers.get(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return customer;
    }

    public void getCustomerMap(Map<Integer, Customer> customers) {
        query = "SELECT * FROM skodatabas.customer;";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!customers.containsKey(rs.getInt("ID"))) {
                    customers.put(rs.getInt("ID"), new Customer(rs.getInt("ID"), rs.getString("Name"), rs.getString("Personal"), rs.getString("Username"), rs.getString("Password")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void getShoesMap(Map<Integer, Shoe> shoes, Map<Integer, Category> categories) {

        query = "SELECT Shoe_ID, Category_ID,"
                + " category.Name as Category,"
                + " shoe.Name as ShoeName,"
                + " price FROM shoe "
                + "inner join categoryassignment on shoe.ID = categoryassignment.Shoe_ID "
                + "inner join category on category.ID = categoryassignment.Category_ID;";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!categories.containsKey(rs.getInt("Category_ID"))) {
                    categories.put(rs.getInt("Category_ID"), new Category(rs.getInt("Category_ID"), rs.getString("Category")));
                }
                if (!shoes.containsKey(rs.getInt("Shoe_ID"))) {
                    shoes.put(rs.getInt("Shoe_ID"), new Shoe(rs.getInt("Shoe_ID"), rs.getString("ShoeName"), rs.getFloat("price")));
                    categories.get(rs.getInt("Category_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                } else if (!categories.get(rs.getInt("Category_ID")).getShoeMap().containsKey(rs.getInt("Shoe_ID"))) {
                    categories.get(rs.getInt("Category_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void mapOrdersToCustomers(Map<Integer, Customer> customers, Map<Integer, Order> orders) {
        query = "Select Customer_ID as CustomerID, "
                + "skodatabas.order.id as OrderID, "
                + "skodatabas.order.Expedited as Expedited, "
                + "skodatabas.order.date as Date from skodatabas.order "
                + "inner join customer on customer.id = skodatabas.order.Customer_ID; ";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!orders.containsKey(rs.getInt("OrderID"))) {
                    orders.put(rs.getInt("OrderID"), new Order(rs.getInt("OrderID"), rs.getDate("date"), rs.getBoolean("Expedited")));
                    customers.get(rs.getInt("customerID")).addOrder(orders.get(rs.getInt("OrderID")));
                }
                if (!customers.get(rs.getInt("customerID")).getOrders().containsKey(rs.getInt("OrderID"))) {
                    customers.get(rs.getInt("customerID")).addOrder(orders.get(rs.getInt("OrderID")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void mapProductsForOrdering(Map<Integer, Product> products,
            Map<Integer, Color> colors,
            Map<Integer, Shoe> shoes,
            Map<Integer, Brand> brands,
            Map<Integer, Size> sizes) {

        query = "Select Product.ID as Product_ID, product.Shoe_ID, Color_ID,  Brand_ID, Size_ID, "
                + "color.name as Color, "
                + "shoe.Name as ShoeName, "
                + "brand.name as Brand, "
                + "size.size as Size, "
                + "Price from product "
                + "inner join size on size.id = product.Size_ID "
                + "inner join color on color.id = product.Color_ID "
                + "inner join shoe on shoe.ID = product.Shoe_ID "
                + "inner join brand on brand.id = shoe.Brand_ID;";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (!products.containsKey(rs.getInt("Product_ID"))) {
                    products.put(rs.getInt("Product_ID"), new Product(rs.getInt("Product_ID")));
                }
                if (!colors.containsKey(rs.getInt("Color_ID"))) {
                    colors.put(rs.getInt("Color_ID"), new Color(rs.getInt("Color_ID"), rs.getString("Color")));
                    colors.get(rs.getInt("Color_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                } else if (!colors.get(rs.getInt("Color_ID")).getProductMap().containsKey(rs.getInt("Product_ID"))) {
                    colors.get(rs.getInt("Color_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                }
                if (!shoes.containsKey(rs.getInt("Shoe_ID"))) {
                    shoes.put(rs.getInt("Shoe_ID"), new Shoe(rs.getInt("Shoe_ID"), rs.getString("ShoeName"), rs.getFloat("Price")));
                    shoes.get(rs.getInt("Shoe_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                } else if (!shoes.get(rs.getInt("Shoe_ID")).getProductMap().containsKey(rs.getInt("Product_ID"))) {
                    shoes.get(rs.getInt("Shoe_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                }
                if (!brands.containsKey(rs.getInt("Brand_ID"))) {
                    brands.put(rs.getInt("Brand_ID"), new Brand(rs.getInt("Brand_ID"), rs.getString("Brand")));
                    brands.get(rs.getInt("Brand_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                } else if (!brands.get(rs.getInt("Brand_ID")).getShoeMap().containsKey(rs.getInt("Shoe_ID"))) {
                    brands.get(rs.getInt("Brand_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                }
                if (!sizes.containsKey(rs.getInt("Size_ID"))) {
                    sizes.put(rs.getInt("Size_ID"), new Size(rs.getInt("Size_ID"), rs.getInt("Size")));
                    sizes.get(rs.getInt("Size_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                } else if (!sizes.get(rs.getInt("Size_ID")).getProductMap().containsKey(rs.getInt("Product_ID"))) {
                    sizes.get(rs.getInt("Size_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public String addToCart(String Order_ID, String Customer_ID, String Product_ID) {

        query = "call AddToCart(?,?,?)";
        String errorString = "Allt gick bra!";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                CallableStatement stmt = con.prepareCall(query)) {
            if (!Order_ID.equals(-1 + "")) {
                stmt.setString(1, Order_ID);
            } else {
                stmt.setString(1, null);
            }
            stmt.setString(2, Customer_ID);
            stmt.setString(3, Product_ID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                errorString = rs.getString("error");
            }
        } catch (SQLException ex) {
            System.out.println("WHYYY");
        }
        return errorString;
    }

    public int getProductID(String ShoeID, String ColorID, String SizeID) {
        query = "Select product.id from product where Size_ID = ? and Shoe_ID = ? and Color_ID = ?";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                CallableStatement stmt = con.prepareCall(query)) {
            stmt.setString(1, SizeID);
            stmt.setString(2, ShoeID);
            stmt.setString(3, ColorID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        }
        return 0;
    }

    public void mapProductsToOrders(Map<Integer, Order> orders,
            Map<Integer, Product> products,
            Map<Integer, Color> colors,
            Map<Integer, Shoe> shoes,
            Map<Integer, Brand> brands,
            Map<Integer, Size> sizes,
            Map<Integer, Category> categories) {

        query = "Select Product_ID, Order_ID, product.Shoe_ID, Color_ID,  Brand_ID, Category_ID, Size_ID, "
                + " color.name as Color, "
                + " shoe.Name as ShoeName, "
                + " brand.name as Brand, "
                + " category.name as Category, "
                + " size.size as Size, "
                + " Price from productsinorder "
                + "inner join product on product.ID= productsinorder.Product_ID "
                + "inner join size on size.id = product.Size_ID "
                + "inner join color on color.id = product.Color_ID "
                + "inner join shoe on shoe.ID = product.Shoe_ID "
                + "inner join brand on brand.id = shoe.Brand_ID "
                + "inner join categoryAssignment on Shoe.ID = categoryAssignment.shoe_ID "
                + "inner join category on categoryAssignment.Category_ID = category.ID; ";

        try (Connection con = DriverManager.getConnection(pr.getConnectionString(), pr.getUsername(), pr.getPassword());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (!products.containsKey(rs.getInt("Product_ID"))) {
                    products.put(rs.getInt("Product_ID"), new Product(rs.getInt("Product_ID")));
                }
                orders.get(rs.getInt("Order_ID")).addProductList(products.get(rs.getInt("Product_ID")));

                if (!colors.containsKey(rs.getInt("Color_ID"))) {
                    colors.put(rs.getInt("Color_ID"), new Color(rs.getInt("Color_ID"), rs.getString("Color")));
                    colors.get(rs.getInt("Color_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                } else if (!colors.get(rs.getInt("Color_ID")).getProductMap().containsKey(rs.getInt("Product_ID"))) {
                    colors.get(rs.getInt("Color_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                }
                if (!shoes.containsKey(rs.getInt("Shoe_ID"))) {
                    shoes.put(rs.getInt("Shoe_ID"), new Shoe(rs.getInt("Shoe_ID"), rs.getString("ShoeName"), rs.getFloat("Price")));
                    shoes.get(rs.getInt("Shoe_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                } else if (!shoes.get(rs.getInt("Shoe_ID")).getProductMap().containsKey(rs.getInt("Product_ID"))) {
                    shoes.get(rs.getInt("Shoe_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                }
                if (!brands.containsKey(rs.getInt("Brand_ID"))) {
                    brands.put(rs.getInt("Brand_ID"), new Brand(rs.getInt("Brand_ID"), rs.getString("Brand")));
                    brands.get(rs.getInt("Brand_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                } else if (!brands.get(rs.getInt("Brand_ID")).getShoeMap().containsKey(rs.getInt("Shoe_ID"))) {
                    brands.get(rs.getInt("Brand_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                }
                if (!sizes.containsKey(rs.getInt("Size_ID"))) {
                    sizes.put(rs.getInt("Size_ID"), new Size(rs.getInt("Size_ID"), rs.getInt("Size")));
                    sizes.get(rs.getInt("Size_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                } else if (!sizes.get(rs.getInt("Size_ID")).getProductMap().containsKey(rs.getInt("Product_ID"))) {
                    sizes.get(rs.getInt("Size_ID")).addProduct(products.get(rs.getInt("Product_ID")));
                }
                if (!categories.containsKey(rs.getInt("Category_ID"))) {
                    categories.put(rs.getInt("Category_ID"), new Category(rs.getInt("Category_ID"), rs.getString("Category")));
                    categories.get(rs.getInt("Category_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                } else if (!categories.get(rs.getInt("Category_ID")).getShoeMap().containsKey(rs.getInt("Shoe_ID"))) {
                    categories.get(rs.getInt("Category_ID")).addShoe(shoes.get(rs.getInt("Shoe_ID")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }
}
