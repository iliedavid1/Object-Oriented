package updates;

import enums.Category;
import enums.ElvesType;

import java.util.ArrayList;

public class ChildrenUpdates {
    private int id;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private ElvesType elf;

    public ChildrenUpdates() { }
    /**
     * getter for id
     */
    public int getId() {
        return id;
    }
    /**
     * setter for id
     * @param id
     * the argument used to set the id with it
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**
     * getter for niceScore
     */
    public Double getNiceScore() {
        return niceScore;
    }
    /**
     * setter for niceScore
     * @param niceScore
     * the argument used to set the niceScore with it
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }
    /**
     * getter for giftsPreferences
     */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }
    /**
     * setter for giftsPreferences
     * @param giftsPreferences
     * the argument used to set the giftsPreferences with it
     */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
    /**
     * getter for elf
     */
    public ElvesType getElf() {
        return elf;
    }
    /**
     * setter for slf
     * @param elf
     * the argument used to set the elf with it
     */
    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }
}
