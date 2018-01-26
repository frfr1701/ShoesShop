package shoesshop;

public class Review {
    
    private final int id;
    private final Shoe shoe;
    private final Rating rating;
    private final Customer customer;
    private final String text;

    public Review(int id, Shoe shoe, Rating rating, Customer customer, String text) {
        this.id = id;
        this.shoe = shoe;
        this.rating = rating;
        this.customer = customer;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Rating getRating() {
        return rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getText() {
        return text;
    }
}
