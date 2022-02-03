package childrenprocessing;

import common.Constants;
import entities.Child;

public class TeenStrategy implements ChildStrategy {
    /**
     * This method is used to calculate the average score for a Teen
     * @param child
     * the argument used to get the child that needs to get score calculated
     */
    @Override
    public double getAverageScore(final Child child) {
        double sum = 0;
        int nr = 0;
        for (Double score : child.getNiceScoreHistory()) {
            nr++;
            sum = sum + nr * score;
        }
        double average = 2 * sum / (nr * (nr + 1));

        average = average + average * child.getNiceScoreBonus()
                / Constants.AVERAGE_SCORE_FORMULA_100;

        if (average > Constants.MAX_AVG_SCORE) {
            average = Constants.MAX_AVG_SCORE;
        }

        return average;
    }
}
