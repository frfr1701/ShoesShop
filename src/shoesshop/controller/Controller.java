package shoesshop.controller;

import shoesshop.model.*;

public class Controller {

    private final SuperModel model;

    public Controller(SuperModel model) {
        this.model = model;
    }

    public SuperModel updateModel(String input) {
        RepoInterface getCustomers = (m, r) -> m.setCustomers(r.getCustomerMap(input));
        model.update(getCustomers);
        return model;
    }
}
