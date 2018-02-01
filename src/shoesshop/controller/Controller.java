package shoesshop.controller;

import static shoesshop.controller.State.*;
import shoesshop.model.*;

public class Controller {

    private final SuperModel model;
    private State state;

    public Controller(SuperModel model) {
        state = START;
        this.model = model;
    }

    public SuperModel updateModel(String input) {
        RepoInterface logIn = (m, r) -> m.setCustomer(r.logIn(m.getUsername(), m.getPassword(), m.getCustomerMap()));
        RepoInterface loadCustomers = (m, r) -> r.getCustomerMap(m.getCustomerMap());
        RepoInterface loadMakeOrder = (m, r) -> m.setErrorString(r.addToCart(m.getAddToCart().get(0), m.getAddToCart().get(1), m.getAddToCart().get(2)));
        RepoInterface loadProductId = (m, r) -> m.setProductID(r.getProductID(m.getTempMakeOrderList().get(0), m.getTempMakeOrderList().get(1), m.getTempMakeOrderList().get(2)));
        RepoInterface loadShoes = (m, r) -> r.getShoesMap(m.getShoeMap(), m.getCategoryMap());
        RepoInterface loadProductsForOrdering = (m, r) -> r.mapProductsForOrdering(m.getProductMap(), m.getColorMap(), m.getShoeMap(), m.getBrandMap(), m.getSizeMap());
        RepoInterface loadOrders = (m, r) -> r.mapOrdersToCustomers(m.getCustomerMap(), m.getOrderMap());
        RepoInterface loadProductsInOrder = (m, r) -> r.mapProductsToOrders(m.getOrderMap(), m.getProductMap(), m.getColorMap(), m.getShoeMap(), m.getBrandMap(), m.getSizeMap());

        model.getOrderMap().forEach((t, u) -> {
            u.getProductList().clear();
        });
        try {
            switch (state) {
                case USERNAME:
                    model.getViewList().clear();
                    model.setUsername(input);
                    model.getViewList().add("Password");
                    state = PASSWORD;
                    break;
                case PASSWORD:
                    model.getViewList().clear();
                    model.setPassword(input);
                    model.update(logIn);
                    model.update(loadCustomers);
                    if (model.getCustomer() == null) {
                        model.getViewList().add("Wrong Username or Password");
                        model.getViewList().add("Username");
                        state = USERNAME;
                    } else {
                        model.getViewList().add("Welcome " + model.getCustomer().getName());
                        AddMenyOptions();
                        state = OPTION;
                    }
                    break;
                case OPTION:
                    model.getViewList().clear();
                    switch (input) {
                        case "1":
                            model.getTempOrderList().clear();
                            model.update(loadOrders);
                            model.getCustomer().getOrders().forEach((t, order) -> {
                                if (!order.isExpedited()) {
                                    model.getTempOrderList().add(order);
                                }
                            });
                            if (!model.getTempOrderList().isEmpty()) {
                                model.getViewList().add("Orders:");
                                model.getTempOrderList().stream().forEach((t) -> {
                                    model.getViewList().add("[" + (model.getTempOrderList().indexOf(t) + 1) + "] " + t.getDate().toString());
                                });
                                model.getViewList().add("\nChoose an Order");
                                state = SHOWORDER;
                            } else {
                                model.getViewList().clear();
                                model.getViewList().add("You have no active orders");
                                AddMenyOptions();
                            }
                            break;
                        case "2":
                            model.update(loadOrders);
                            model.getViewList().add("Write CustomerID or nothing");
                            state = SHOWALLCUSTOMERS;
                            break;
                        case "3":
                            model.update(loadShoes);
                            model.getCategoryMap().entrySet().forEach((t) -> {
                                model.getViewList().add(t.getValue().getName());
                                t.getValue().getShoeMap().entrySet().forEach((u) -> {
                                    model.getViewList().add(u.getValue().getName());
                                });
                                model.getViewList().add("");
                            });
                            state = OPTION;
                            AddMenyOptions();
                            break;
                        case "4":
                            model.update(loadShoes);
                            model.update(loadOrders);
                            model.update(loadProductsForOrdering);
                            model.getTempShoeList().clear();
                            model.getTempColorList().clear();
                            model.getTempSizeList().clear();
                            model.getShoeMap().entrySet().forEach((t) -> {
                                model.getTempShoeList().add(t.getValue());
                                model.getViewList().add("[" + (model.getTempShoeList().indexOf(t.getValue()) + 1) + "] " + t.getValue().getName());
                            });
                            state = CHOOSECOLOR;
                            break;
                        case "5":
                            state = USERNAME;
                            model.getViewList().add("Username");
                            break;
                        default:
                    }
                    break;
                case SHOWORDER:
                    model.getViewList().clear();
                    try {
                        model.update(loadProductsInOrder);
                        model.getOrderMap().forEach((key, order) -> {
                            if (model.getTempOrderList().get(Integer.parseInt(input) - 1).equals(order)) {
                                model.getViewList().add("");

                                System.out.println(order.getProductList().size());

                                order.getProductList().forEach((t) -> {

                                    model.getColorMap().values().stream().forEach((x) -> {
                                        if (x.getProductMap().containsKey(t.getId())) {
                                            model.getViewList().add(x.getName());
                                        }
                                    });
                                    model.getShoeMap().values().stream().forEach((x) -> {
                                        if (x.getProductMap().containsKey(t.getId())) {
                                            model.getViewList().add(x.getName());
                                            model.getBrandMap().values().stream().forEach((y) -> {
                                                if (y.getShoeMap().containsValue(x)) {
                                                    model.getViewList().add(y.getName());
                                                }
                                            });
                                        }
                                    });
                                    model.getSizeMap().values().stream().forEach((x) -> {
                                        if (x.getProductMap().containsKey(t.getId())) {
                                            model.getViewList().add(String.valueOf(x.getSize() + "\n"));
                                        }
                                    });
                                });
                            }
                        });
                    } catch (NumberFormatException | IndexOutOfBoundsException numberFormatException) {
                        model.getViewList().add("You can't select that");
                    } finally {
                        model.getTempOrderList().clear();
                        AddMenyOptions();
                        state = OPTION;
                    }
                    break;
                case SHOWALLCUSTOMERS:
                    model.getViewList().clear();
                    model.getTempDoubleList().clear();
                    model.update(loadProductsInOrder);
                    if (input.equalsIgnoreCase("")) {
                        model.getCustomerMap().entrySet().stream().forEach((t) -> {
                            model.getTempDoubleList().clear();
                            model.getViewList().add(t.getValue().getName());
                            t.getValue().getOrders().entrySet().forEach((u) -> {
                                u.getValue().getProductList().forEach((v) -> {
                                    model.getShoeMap().entrySet().forEach((x) -> {
                                        if (x.getValue().getProductMap().containsKey(v.getId())) {
                                            model.getTempDoubleList().add(x.getValue().getPrice() + 0.0);
                                        }
                                    });
                                });
                            });
                            model.getViewList().add(model.getTempDoubleList().stream().mapToDouble(Double::doubleValue).sum() + "");
                        });
                    } else {
                        model.getCustomerMap().entrySet().stream().forEach((t) -> {
                            if ((t.getValue().getId() + "").equalsIgnoreCase(input)) {
                                model.getTempDoubleList().clear();
                                model.getViewList().add(t.getValue().getName());
                                t.getValue().getOrders().entrySet().forEach((u) -> {
                                    u.getValue().getProductList().forEach((v) -> {
                                        model.getShoeMap().entrySet().forEach((x) -> {
                                            if (x.getValue().getProductMap().containsKey(v.getId())) {
                                                model.getTempDoubleList().add(x.getValue().getPrice() + 0.0);
                                            }
                                        });
                                    });
                                });
                                model.getViewList().add(model.getTempDoubleList().stream().mapToDouble(Double::doubleValue).sum() + "");
                            }
                        });
                    }
                    AddMenyOptions();
                    state = OPTION;

                    break;
                case CHOOSECOLOR:
                    model.getViewList().clear();
                    model.getShoeMap().entrySet().forEach((t) -> {
                        if (t.getValue().equals(model.getTempShoeList().get(Integer.parseInt(input) - 1))) {
                            model.getTempMakeOrderList().add(t.getValue().getId() + "");
                            t.getValue().getProductMap().values().forEach((u) -> {
                                model.getColorMap().values().forEach((v) -> {
                                    if (v.getProductMap().values().contains(u)) {
                                        model.getTempColorList().add(v);
                                        model.getViewList().add("[" + (model.getTempColorList().indexOf(v) + 1) + "] " + v.getName());
                                    }
                                });
                            });
                        }
                    });

                    state = CHOOSESIZE;
                    break;
                case CHOOSESIZE:
                    model.getTempMakeOrderList().add(model.getTempColorList().get(Integer.parseInt(input) - 1).getId() + "");
                    model.getViewList().clear();
                    model.getShoeMap().entrySet().forEach((t) -> {
                        if (t.getKey().equals((Integer.parseInt(model.getTempMakeOrderList().get(0))))) {
                            t.getValue().getProductMap().values().forEach((u) -> {
                                model.getColorMap().values().forEach((v) -> {
                                    if (v.getId() == (Integer.parseInt(model.getTempMakeOrderList().get(1)))) {
                                        model.getSizeMap().entrySet().forEach((x) -> {
                                            if (x.getValue().getProductMap().containsValue(u)) {
                                                model.getTempSizeList().add(x.getValue());
                                                model.getViewList().add("[" + (model.getTempSizeList().indexOf(x.getValue()) + 1) + "] " + x.getValue().getSize());
                                            }
                                        });
                                    }
                                });

                            });
                        }
                    });
                    state = CHOOSEORDER;
                    break;
                case CHOOSEORDER:
                    model.getViewList().clear();
                    model.getTempOrderList().clear();
                    model.getTempMakeOrderList().add(model.getTempSizeList().get(Integer.parseInt(input) - 1).getId() + "");

                    model.getCustomer().getOrders().values().forEach((order) -> {
                        if (!order.isExpedited()) {
                            model.getTempOrderList().add(order);
                        }
                    });
                    if (!model.getTempOrderList().isEmpty()) {
                        model.getViewList().add("Orders:");
                        model.getTempOrderList().stream().forEach((t) -> {
                            model.getViewList().add("[" + (model.getTempOrderList().indexOf(t) + 1) + "] " + t.getDate().toString());
                        });
                    } else {
                        model.getViewList().clear();
                        model.getViewList().add("You have no active orders");
                    }
                    model.getViewList().add("[" + (model.getTempOrderList().size() + 1) + "] create new order");
                    state = MAKETHEORDER;
                    break;

                case MAKETHEORDER:
                    model.getAddToCart().clear();
                    model.getViewList().clear();
                    model.update(loadProductId);

                    if (Integer.parseInt(input) <= model.getTempOrderList().size()) {
                        model.getAddToCart().add(model.getTempOrderList().get(Integer.parseInt(input) - 1).getId() + "");
                        model.getAddToCart().add(model.getCustomer().getId() + "");
                        model.getAddToCart().add(model.getProductID() + "");

                    } else {
                        model.getAddToCart().add(-1 + "");
                        model.getAddToCart().add(model.getCustomer().getId() + "");
                        model.getAddToCart().add(model.getProductID() + "");
                    }
                    model.update(loadMakeOrder);
                    model.getViewList().add(model.getErrorString());
                    model.getTempMakeOrderList().clear();
                    model.getTempOrderList().clear();
                    AddMenyOptions();
                    state = OPTION;
                    break;
                default:
                    model.getViewList().add("Username");
                    state = USERNAME;
            }
        } catch (IndexOutOfBoundsException e) {
            model.getViewList().clear();
            model.getViewList().add("You can't select that");
            AddMenyOptions();
            state = OPTION;
        }
        return model;
    }

    private void AddMenyOptions() {
        model.getViewList().add("");
        model.getViewList().add("What do you wan't to do?");
        model.getViewList().add("[1] Check your orders");
        model.getViewList().add("[2] Show Customer List");
        model.getViewList().add("[3] Show all Products by Categories");
        model.getViewList().add("[4] Make an order");
        model.getViewList().add("[5] Log out");
    }
}
