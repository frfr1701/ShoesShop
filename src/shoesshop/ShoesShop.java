package shoesshop;

import shoesshop.controller.*;
import shoesshop.model.*;
import shoesshop.repository.*;
import shoesshop.view.*;

public class ShoesShop {

    public static void main(String[] args) {
        ShoesShop go = new ShoesShop();
        go.go();
    }

    private void go() {
        Repository repository = new Repository();
        SuperModel model = new SuperModel(repository);
        Controller controller = new Controller(model);
        View view = new View(controller);
        view.display(controller.updateModel(""));
    }
}
