package assignprocessing;

import database.Repository;
import entities.Child;

import java.util.Collections;
import java.util.Comparator;

public class NiceScoreCityStrategy implements AssignStrategy {
    /**
     * This method is used to get the right order for assignation
     */
    @Override
    public void getOrder() {
        for (Child child : Repository.getInstance().getChildrenData()) {
            child.setNiceScoreCity(Repository.getInstance().getCityScore(child.getCity()));
        }

        Collections.sort(Repository.getInstance().getChildrenData(), new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                if (o1.getNiceScoreCity() == o2.getNiceScoreCity()) {
                    if (o1.getCity().equals(o2.getCity())) {
                        return o1.getId() - o2.getId();
                    }
                    return o1.getCity().toString().compareTo(o2.getCity().toString());
                } else if (o1.getNiceScoreCity() < o2.getNiceScoreCity()) {
                    return 1;
                }
                return -1;
            }
        });
    }
}
