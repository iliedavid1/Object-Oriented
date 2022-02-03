package assignprocessing;

import database.Repository;
import entities.Child;

import java.util.Collections;
import java.util.Comparator;

public class NiceScoreStrategy implements AssignStrategy {
    /**
     * This method is used to get the right order for assignation
     */
    @Override
    public void getOrder() {
        Collections.sort(Repository.getInstance().getChildrenData(), new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                if (o1.getNiceScoreAverage() == o2.getNiceScoreAverage()) {
                    return o1.getId() - o2.getId();
                } else if (o1.getNiceScoreAverage() < o2.getNiceScoreAverage()) {
                    return 1;
                }
                return -1;
            }
        });
    }
}
