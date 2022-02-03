package updates;

import entities.Child;
import entities.Gift;
import enums.CityStrategyEnum;

import java.util.ArrayList;

public class AnnualChanges {
    private double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildrenUpdates> childrenUpdates;
    private CityStrategyEnum strategy;

    public AnnualChanges() { }
    /**
     * getter for newSantaBudget
     */
    public double getNewSantaBudget() {
        return newSantaBudget;
    }
    /**
     * setter for newSantaBudget
     * @param newSantaBudget
     * the argument used to set the newSantaBudget with it
     */
    public void setNewSantaBudget(final double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }
    /**
     * getter for newGifts
     */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }
    /**
     * setter for newGifts
     * @param newGifts
     * the argument used to set the newGifts with it
     */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }
    /**
     * getter for newChildren
     */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }
    /**
     * setter for newChildren
     * @param newChildren
     * the argument used to set the newChildren with it
     */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }
    /**
     * getter for childrenUpdates
     */
    public ArrayList<ChildrenUpdates> getChildrenUpdates() {
        return childrenUpdates;
    }
    /**
     * setter for childrenUpdates
     * @param childrenUpdates
     * the argument used to set the childrenUpdates with it
     */
    public void setChildrenUpdates(final ArrayList<ChildrenUpdates> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
    /**
     * getter for strategy
     */
    public CityStrategyEnum getStrategy() {
        return strategy;
    }
    /**
     * setter for strategy
     * @param strategy
     * the argument used to set the strategy with it
     */
    public void setStrategy(final CityStrategyEnum strategy) {
        this.strategy = strategy;
    }
}
