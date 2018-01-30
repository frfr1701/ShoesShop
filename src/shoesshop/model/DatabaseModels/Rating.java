package shoesshop.model.DatabaseModels;

import java.util.*;

public class Rating {

    private final int id;
    private final int optionsValue;
    private final String option;
    private final Map<Integer, Review> reviewMap;
    

    public Rating(int id, int optionsValue, String option) {
        this.reviewMap = new HashMap<>();
        this.id = id;
        this.optionsValue = optionsValue;
        this.option = option;
    }
    
    public int getId() {
        return id;
    }
    
    public Map<Integer, Review> getReviewsMap() {
        return reviewMap;
    }
    
    public void addReview(Review review) {
        reviewMap.put(review.getId(), review);
    }

    public void removeReview(Review review) {
        reviewMap.remove(review.getId());
    }

    public int getOptionsValue() {
        return optionsValue;
    }

    public String getOption() {
        return option;
    }
}
