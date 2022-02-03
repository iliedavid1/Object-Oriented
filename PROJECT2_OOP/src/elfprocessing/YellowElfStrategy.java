package elfprocessing;

import entities.Child;

public class YellowElfStrategy implements ElfStrategy {
    /**
     * method to calculate the new budget for Yellow Elf
     * @param child
     * the argument used to get the old budget
     */
    @Override
    public double getNewBudget(final Child child) {
        return child.getAssignedBudget();
    }
}
