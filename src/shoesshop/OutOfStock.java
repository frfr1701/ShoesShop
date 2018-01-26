package shoesshop;

import java.util.*;

public class OutOfStock {

    private final int id;
    private final Date date;
    private final Product product;

    public OutOfStock(int id, Date date, Product product) {
        this.id = id;
        this.date = date;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }

}
