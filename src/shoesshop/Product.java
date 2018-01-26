package shoesshop;

public class Product {

    private final int id;
    private final Shoe shoe;
    private final Size size;
    private final Color color;

    public Product(int id, Shoe shoe, Size size, Color color) {
        this.id = id;
        this.shoe = shoe;
        this.size = size;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}
