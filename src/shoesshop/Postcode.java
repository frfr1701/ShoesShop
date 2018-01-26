package shoesshop;

public class Postcode {

    private final int id;
    private final String nummer;

    public Postcode(int id, String nummer) {
        this.id = id;
        this.nummer = nummer;
    }

    public int getId() {
        return id;
    }

    public String getNummer() {
        return nummer;
    }
}
