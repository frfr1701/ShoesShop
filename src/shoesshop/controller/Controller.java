package shoesshop.controller;

import static shoesshop.controller.State.*;
import shoesshop.model.*;

public class Controller {

    private final SuperModel model;
    private State state;
    private String username, password;

    public Controller(SuperModel model) {
        state = START;
        this.model = model;
    }

    public SuperModel updateModel(String input) {
        RepoInterface logIn = (m, r) -> m.setCustomer(r.logIn(username, password));
        RepoInterface loadOrders = (m, r) -> r.mapOrdersToCustomers(m.getCustomerMap());
//        RepoInterface loadProductsInOrder = (m, r) -> r.mapProductsToOrders(m.getCustomers());

        switch (state) {
            case USERNAME:
                model.getViewList().clear();
                username = input;
                model.getViewList().add("Password");
                state = PASSWORD;
                break;
            case PASSWORD:
                model.getViewList().clear();
                password = input;
                model.update(logIn);
                if (model.getCustomer() == null) {
                    model.getViewList().add("Wrong Username or Password");
                    model.getViewList().add("Username");
                    state = USERNAME;
                } else {
                    model.addCustomer(model.getCustomer());
                    model.getViewList().add("Welcome " + username);
                    model.getViewList().add("What do you wan't to do?");
                    model.getViewList().add("[1] check your orders");
                    state = OPTION;
                }
                break;
            case OPTION:
                model.getViewList().clear();
                switch (input) {
                    case "1":
                        model.update(loadOrders);
                        model.getViewList().add("Orders:");
                        model.getCustomer().getOrders().forEach((t, order) -> {
                            if (!order.isExpedited()) {
                                model.getViewList().add(order.getDate().toString());
                            }
                        });
                        break;
                    default:
                }
                break;
            default:
                model.getViewList().add("Username");
                state = USERNAME;
        }
        return model;
    }
}
