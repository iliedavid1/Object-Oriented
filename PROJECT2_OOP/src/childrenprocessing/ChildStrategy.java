package childrenprocessing;

import entities.Child;

public interface ChildStrategy {
    /**
     * This method is used to calculate the average score for a child
     * @param child
     * the argument used to get the child that needs to get score calculated
     */
    double getAverageScore(Child child);
}
