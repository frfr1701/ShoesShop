package shoesshop.model.DatabaseModels;

import shoesshop.model.DatabaseModels.Shoe;
import shoesshop.model.DatabaseModels.Rating;
import shoesshop.model.DatabaseModels.Customer;

public class Review {

    private final int id;
    private Shoe shoe;
    private Rating rating;
    private Customer customer;
    private String text;

    public Review(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
