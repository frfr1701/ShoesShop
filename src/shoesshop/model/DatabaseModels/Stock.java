package shoesshop.model.DatabaseModels;

public class Stock {

    private final int id;
    private int quantity;

    public Stock(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
