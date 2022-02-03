package childrenprocessing;

import common.Constants;
import entities.Child;

public class BabyStrategy implements ChildStrategy {
    /**
     * This method is used to calculate the average score for a Baby
     * @param child
     * the argument used to get the child that needs to get score calculated
     */
    @Override
    public double getAverageScore(final Child child) {
        return Constants.AVERAGE_SCORE_BABY;
    }
}
