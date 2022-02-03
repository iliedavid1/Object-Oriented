package database;

import entities.Child;
import entities.Gift;
import enums.Cities;
import updates.AnnualChanges;

import java.util.ArrayList;
import java.util.Observable;

public final class Repository extends Observable {

    private int numberYears;

    private double budget;

    private double bugdetUnit;

    private ArrayList<Child> childrenData;

    private ArrayList<Gift> giftsData;

    private ArrayList<AnnualChanges> annualChangesData;

    private final static Repository INSTANCE = new Repository();

    private Repository() { }

    public static Repository getInstance() {
        return INSTANCE;
    }

    public int getNumberYears() {
        return numberYears;
    }

    public double getBudget() {
        return budget;
    }

    public ArrayList<Child> getChildrenData() {
        return childrenData;
    }

    public ArrayList<Gift> getGiftsData() {
        return giftsData;
    }

    public ArrayList<AnnualChanges> getAnnualChangesData() {
        return annualChangesData;
    }

    public void setNumberYears(final int numberYears) {
        this.numberYears = numberYears;
    }

    public void setBudget(final double budget) {
        this.budget = budget;
    }

    public void setChildrenData(final ArrayList<Child> childrenData) {
        this.childrenData = childrenData;
    }

    public void setGiftsData(final ArrayList<Gift> giftsData) {
        this.giftsData = giftsData;
    }

    public void setAnnualChangesData(final ArrayList<AnnualChanges> annualChangesData) {
        this.annualChangesData = annualChangesData;
    }

    public double getBugdetUnit() {
        return bugdetUnit;
    }

    public void setBugdetUnit(final double bugdetUnit) {
        this.bugdetUnit = bugdetUnit;
    }
    /**
     * This method is used to clear all the data from a test
     */
    public void clearAll() {
        this.childrenData.clear();
        this.annualChangesData.clear();
        this.giftsData.clear();
    }
    /**
     * calculate the average city score
     * @param city
     * the argument used to get the score for the city
     */
    public double getCityScore(final Cities city) {
        int nr = 0;
        double sum = 0;
        for (Child child : Repository.getInstance().getChildrenData()) {
            if (child.getCity().equals(city)) {
                sum = sum + child.getNiceScoreAverage();
                nr++;
            }
        }
        return sum / nr;
    }
    /**
     * method that is used for updating the Repository
     * @param annualChanges
     * the argument used to modify Repository after it
     */
    public void updateRepository(final AnnualChanges annualChanges) {
        setChanged();
        notifyObservers(annualChanges);
    }
}
