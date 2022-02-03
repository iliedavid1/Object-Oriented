package childrenprocessing;

import common.Constants;
import entities.Child;

public class KidStrategy implements ChildStrategy {
    /**
     * This method is used to calculate the average score for a Kid
     * @param child
     * the argument used to get the child that needs to get score calculated
     */
    @Override
    public double getAverageScore(final Child child) {
        double sum = 0;

        for (Double score : child.getNiceScoreHistory()) {
            sum = sum + score;
        }
        double average = sum / child.getNiceScoreHistory().size();

        average = average + average * child.getNiceScoreBonus()
                / Constants.AVERAGE_SCORE_FORMULA_100;

        if (average > Constants.MAX_AVG_SCORE) {
            average = Constants.MAX_AVG_SCORE;
        }

        return average;
    }
}
