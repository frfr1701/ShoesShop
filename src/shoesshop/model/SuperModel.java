package shoesshop.model;

import shoesshop.model.DatabaseModels.*;
import shoesshop.controller.*;
import shoesshop.repository.*;
import java.util.*;

public class SuperModel {
    private int productID;
    private String username, password, errorString;

    private final Repository repository;
    private final List<String> viewList;
    private final List<Order> tempOrderList;
    private final List<Double> tempDoubleList;
    private final List<Shoe> tempShoeList;
    private final List<Color> tempColorList;
    private final List<Size> tempSizeList;
    private final List<String> tempMakeOrderList;
    private final List<String> addToCart;
    private Customer customer;
    
    private final Map<Integer, Customer> customerMap;
    private final Map<Integer, CustomerLocation> customerLocationMap;
    private final Map<Integer, City> cityMap;
    private final Map<Integer, Postcode> postcodeMap;
    
    private final Map<Integer, Order> orderMap;
    
    private final Map<Integer, Product> productMap;
    private final Map<Integer, Color> colorMap;
    private final Map<Integer, Size> sizeMap;
    private final Map<Integer, Stock> stockMap;
    private final Map<Integer, OutOfStock> outOfStockMap;
    
    private final Map<Integer, Shoe> shoeMap;
    private final Map<Integer, Brand> brandMap;
    private final Map<Integer, Category> categoryMap;
    
    private final Map<Integer, Review> reviewMap;
    private final Map<Integer, Rating> RatingMap;
    
    public SuperModel(Repository repository) {
        this.RatingMap = new HashMap<>();
        this.reviewMap = new HashMap<>();
        this.categoryMap = new HashMap<>();
        this.brandMap = new HashMap<>();
        this.outOfStockMap = new HashMap<>();
        this.stockMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
        this.colorMap = new HashMap<>();
        this.productMap = new HashMap<>();
        this.orderMap = new HashMap<>();
        this.postcodeMap = new HashMap<>();
        this.cityMap = new HashMap<>();
        this.customerLocationMap = new HashMap<>();
        this.customerMap = new HashMap<>();
        this.shoeMap = new HashMap<>();
        
        this.viewList = new ArrayList<>();
        this.tempOrderList = new ArrayList<>();
        this.tempDoubleList = new ArrayList<>();
        this.repository = repository;
        this.tempShoeList = new ArrayList<>();
        this.tempColorList = new ArrayList<>();
        this.tempSizeList = new ArrayList<>();
        this.addToCart = new ArrayList<>();
        this.tempMakeOrderList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
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

    public List<Double> getTempDoubleList() {
        return tempDoubleList;
    }

    public List<Size> getTempSizeList() {
        return tempSizeList;
    }

    public List<String> getAddToCart() {
        return addToCart;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    public List<String> getTempMakeOrderList() {
        return tempMakeOrderList;
    }
    
    public List<Shoe> getTempShoeList() {
        return tempShoeList;
    }

    public List<Color> getTempColorList() {
        return tempColorList;
    }
    
    public List<String> getViewList() {
        return viewList;
    }

    public List<Order> getTempOrderList() {
        return tempOrderList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Map<Integer, Customer> getCustomerMap() {
        return customerMap;
    }

    public Map<Integer, Shoe> getShoeMap() {
        return shoeMap;
    }

    public Repository getRepository() {
        return repository;
    }

    public Map<Integer, CustomerLocation> getCustomerLocationMap() {
        return customerLocationMap;
    }

    public Map<Integer, City> getCityMap() {
        return cityMap;
    }

    public Map<Integer, Postcode> getPostcodeMap() {
        return postcodeMap;
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public Map<Integer, Color> getColorMap() {
        return colorMap;
    }

    public Map<Integer, Size> getSizeMap() {
        return sizeMap;
    }

    public Map<Integer, Stock> getStockMap() {
        return stockMap;
    }

    public Map<Integer, OutOfStock> getOutOfStockMap() {
        return outOfStockMap;
    }

    public Map<Integer, Brand> getBrandMap() {
        return brandMap;
    }

    public Map<Integer, Category> getCategoryMap() {
        return categoryMap;
    }

    public Map<Integer, Review> getReviewMap() {
        return reviewMap;
    }

    public Map<Integer, Rating> getRatingMap() {
        return RatingMap;
    }
    
    public RepoInterface update(RepoInterface f) {
        f.update(this, repository);
        return f;
    }
}