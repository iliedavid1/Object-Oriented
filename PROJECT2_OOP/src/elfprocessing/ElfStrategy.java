package elfprocessing;

import entities.Child;

public interface ElfStrategy {
    /**
     * method to calculate the new budget depending on elves
     * @param child
     * the argument used to get the old budget
     */
    double getNewBudget(Child child);
}
