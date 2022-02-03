package execution;

import database.Repository;
import entities.Child;
import entities.Gift;
import enums.Category;
import updates.AnnualChanges;
import updates.ChildrenUpdates;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public class UpdateRepository implements Observer {
    /**
     * method to update the repository after a year
     * @param arg
     * the argument used to update the repository after it
     */
    @Override
    public void update(final Observable o, final Object arg) {
        AnnualChanges annualChanges = (AnnualChanges) arg;

        Repository.getInstance().setBudget(annualChanges.getNewSantaBudget());
        for (Child child : Repository.getInstance().getChildrenData()) {
            child.getReceivedGifts().clear();
        }
        for (Child child : annualChanges.getNewChildren()) {
            child.getNiceScoreHistory().add(child.getNiceScore());
            Repository.getInstance().getChildrenData().add(child);
        }
        for (Gift gift : annualChanges.getNewGifts()) {
            Repository.getInstance().getGiftsData().add(gift);
        }
        for (ChildrenUpdates childrenUpdates : annualChanges.getChildrenUpdates()) {
            Child child = null;
            for (Child child1 : Repository.getInstance().getChildrenData()) {
                if (child1.getId() == childrenUpdates.getId()) {
                    child = child1;
                    break;
                }
            }
            if (child != null) {
                if (childrenUpdates.getNiceScore() != null) {
                    child.getNiceScoreHistory().add(childrenUpdates.getNiceScore());
                }
                child.setElf(childrenUpdates.getElf());
                Collections.reverse(childrenUpdates.getGiftsPreferences());
                for (Category category : childrenUpdates.getGiftsPreferences()) {
                    child.getGiftsPreferences().remove(category);
                    child.getGiftsPreferences().add(0, category);
                }
            }
        }
    }
}
