package shoesshop.model.DatabaseModels;

public class Product {

    private final int id;
    private Shoe shoe;
    private Size size;
    private Color color;

    public Product(int id) {
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
