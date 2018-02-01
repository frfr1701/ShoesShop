package shoesshop.model.DatabaseModels;

import java.util.*;

public class OutOfStock {

    private final int id;
    private final Date date;

    public OutOfStock(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
