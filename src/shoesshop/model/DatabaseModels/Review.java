package shoesshop.model.DatabaseModels;

public class Review {

    private final int id;
    private String text;

    public Review(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
