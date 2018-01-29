package shoesshop.model.DatabaseModels;

import shoesshop.model.DatabaseModels.Product;

public class Stock {

    private final int id;
    private final Product product;
    private int quantity;

    public Stock(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
