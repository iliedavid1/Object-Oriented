package elfprocessing;

import common.Constants;
import entities.Child;

public class BlackElfStrategy implements ElfStrategy {
    /**
     * method to calculate the new budget for Black Elf
     * @param child
     * the argument used to get the old budget
     */
    @Override
    public double getNewBudget(final Child child) {
        double budget = child.getAssignedBudget();

        budget = budget - budget * Constants.THIRTY_PERCENT
                / Constants.AVERAGE_SCORE_FORMULA_100;
        return budget;
    }
}
