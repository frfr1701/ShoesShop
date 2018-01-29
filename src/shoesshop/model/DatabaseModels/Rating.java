package shoesshop.model.DatabaseModels;

public class Rating {

    private final int id;
    private final int optionsValue;
    private final String option;

    public Rating(int id, int optionsValue, String option) {
        this.id = id;
        this.optionsValue = optionsValue;
        this.option = option;
    }

    public int getId() {
        return id;
    }

    public int getOptionsValue() {
        return optionsValue;
    }

    public String getOption() {
        return option;
    }
}
