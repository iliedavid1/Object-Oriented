package fileio;

import entities.Child;
import entities.Gift;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public class ChildOutput {
    private int id;
    private String lastName;
    private String firstName;
    private Cities city;
    private int age;
    private ArrayList<Category> giftsPreferences;
    private double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public ChildOutput(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
        this.averageScore = child.getNiceScoreAverage();
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.assignedBudget = child.getAssignedBudget();
        this.receivedGifts = new ArrayList<>(child.getReceivedGifts());
    }
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
     * getter for lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * setter for lastName
     * @param lastName
     * the argument used to set the lastName with it
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    /**
     * getter for firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * setter for firstName
     * @param firstName
     * the argument used to set the firstName with it
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    /**
     * getter for city
     */
    public Cities getCity() {
        return city;
    }
    /**
     * setter for city
     * @param city
     * the argument used to set the city with it
     */
    public void setCity(final Cities city) {
        this.city = city;
    }
    /**
     * getter for age
     */
    public int getAge() {
        return age;
    }
    /**
     * setter for age
     * @param age
     * the argument used to set the age with it
     */
    public void setAge(final int age) {
        this.age = age;
    }
    /**
     * getter for giftPreferences
     */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }
    /**
     * setter for giftPreferences
     * @param giftsPreferences
     * the argument used to set the giftPreferences with it
     */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
    /**
     * getter for averageScore
     */
    public double getAverageScore() {
        return averageScore;
    }
    /**
     * setter for averageScore
     * @param averageScore
     * the argument used to set the averageScore with it
     */
    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }
    /**
     * getter for niceScoreHistory
     */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }
    /**
     * setter for niceScoreHistory
     * @param niceScoreHistory
     * the argument used to set the niceScoreHistory with it
     */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }
    /**
     * getter for assignedBudget
     */
    public double getAssignedBudget() {
        return assignedBudget;
    }
    /**
     * setter for assignedBudget
     * @param assignedBudget
     * the argument used to set the assignedBudget with it
     */
    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }
    /**
     * getter for receivedGifts
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }
    /**
     * setter for receivedGifts
     * @param receivedGifts
     * the argument used to set the receivedGifts with it
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
