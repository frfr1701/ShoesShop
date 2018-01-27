package shoesshop;

public class ShoesShop {

    public static void main(String[] args) {
        ShoesShop go = new ShoesShop();
        go.go();
    }

    private void go() {
        Repository rp = new Repository();
        rp.laddaInKunder();
        
        rp.getCustomers().forEach((k,Customer) -> System.out.println(Customer.getName()));
        
    }
}
