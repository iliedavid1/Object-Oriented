package entities;

import enums.Category;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;

public class Child {
    private int id;
    private String lastName;
    private String firstName;
    private Cities city;
    private int age;
    private ArrayList<Category> giftsPreferences;
    private double niceScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private double niceScoreAverage;
    private double assignedBudget;
    private ArrayList<Gift> receivedGifts = new ArrayList<>();
    private double niceScoreBonus;
    private ElvesType elf;
    private double niceScoreCity;

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
     * getter for niceScore
     */
    public double getNiceScore() {
        return niceScore;
    }
    /**
     * setter for niceScore
     * @param niceScore
     * the argument used to set the niceScore with it
     */
    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }
    /**
     * getter for niceScoreAverage
     */
    public double getNiceScoreAverage() {
        return niceScoreAverage;
    }
    /**
     * setter for niceScoreAverage
     * @param niceScoreAverage
     * the argument used to set the niceScoreAverage with it
     */
    public void setNiceScoreAverage(final double niceScoreAverage) {
        this.niceScoreAverage = niceScoreAverage;
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
    /**
     * getter for elf
     */
    public ElvesType getElf() {
        return elf;
    }
    /**
     * setter for elf
     * @param elf
     * the argument used to set the elf with it
     */
    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }
    /**
     * getter for niceScoreBonus
     */
    public double getNiceScoreBonus() {
        return niceScoreBonus;
    }
    /**
     * setter for niceScoreBonus
     * @param niceScoreBonus
     * the argument used to set the niceScoreBonus with it
     */
    public void setNiceScoreBonus(final double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }
    /**
     * getter for niceScoreCity
     */
    public double getNiceScoreCity() {
        return niceScoreCity;
    }
    /**
     * setter for niceScoreCity
     * @param niceScoreCity
     * the argument used to set the niceScoreCity with it
     */
    public void setNiceScoreCity(final double niceScoreCity) {
        this.niceScoreCity = niceScoreCity;
    }
}
