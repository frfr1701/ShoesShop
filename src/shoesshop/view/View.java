package shoesshop.view;

import java.util.*;
import shoesshop.controller.*;
import shoesshop.model.*;

public class View {

    Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void display(SuperModel model) {
        model.getCustomers().forEach((k, Customer) -> System.out.println(Customer.getName()));

        run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        display(controller.updateModel(line));
    }

    public void start() {
        display(controller.updateModel(""));
    }
}
