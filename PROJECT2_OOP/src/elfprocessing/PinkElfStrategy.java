package elfprocessing;

import common.Constants;
import entities.Child;

public class PinkElfStrategy implements ElfStrategy {
    /**
     * method to calculate the new budget for Pink Elf
     * @param child
     * the argument used to get the old budget
     */
    @Override
    public double getNewBudget(final Child child) {
        double budget = child.getAssignedBudget();

        budget = budget + budget * Constants.THIRTY_PERCENT
                / Constants.AVERAGE_SCORE_FORMULA_100;
        return budget;
    }
}
