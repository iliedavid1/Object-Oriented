package assignprocessing;

import database.Repository;
import entities.Child;

import java.util.Comparator;

public class IdStrategy implements AssignStrategy {
    /**
     * This method is used to get the right order for assignation
     */
    @Override
    public void getOrder() {
        Repository.getInstance().getChildrenData().sort(Comparator.comparingInt(Child::getId));
    }
}
