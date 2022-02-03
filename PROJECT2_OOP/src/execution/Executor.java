package execution;

import assignprocessing.AssignStrategy;
import assignprocessing.AssignStrategyFactory;
import childrenprocessing.ChildStrategy;
import childrenprocessing.ChildStrategyFactory;
import common.Constants;
import database.Repository;
import elfprocessing.ElfStrategy;
import elfprocessing.ElfStrategyFactory;
import entities.Child;
import entities.Gift;
import enums.Category;
import enums.CityStrategyEnum;
import enums.ElvesType;
import fileio.AnnualChildren;
import fileio.Output;
import updates.AnnualChanges;

public class Executor {
    /**
     * method to remove adults from list
     */
    public void removeAdults() {
        Repository.getInstance().getChildrenData().removeIf(
                child -> child.getAge() >= Constants.MIN_ADULTS);
    }
    /**
     * method for first round
     * @param output
     * the argument used to put the output for first round in the output object
     */
    public void firstRound(final Output output) {
        this.removeAdults();
        for (Child child : Repository.getInstance().getChildrenData()) {
            child.getNiceScoreHistory().add(child.getNiceScore());
        }
        this.scoreCalculator();

        double sum = 0;
        for (Child child : Repository.getInstance().getChildrenData()) {
            sum = sum + child.getNiceScoreAverage();
        }
        Repository.getInstance().setBugdetUnit(Repository.getInstance().getBudget() / sum);
        for (Child child : Repository.getInstance().getChildrenData()) {
            child.setAssignedBudget(child.getNiceScoreAverage()
                    * Repository.getInstance().getBugdetUnit());
            ElfStrategy elfStrategy = ElfStrategyFactory.createStrategy(child.getElf());
            child.setAssignedBudget(elfStrategy.getNewBudget(child));
        }

        this.giftsAssignee();

        this.yellowElf();

        AnnualChildren annualChildren = new AnnualChildren();
        annualChildren.addChildren();
        output.getAnnualChildren().add(annualChildren);

    }
    /**
     * method to calculate the average score for children
     */
    public void scoreCalculator() {
        for (Child child : Repository.getInstance().getChildrenData()) {
            ChildStrategy childStrategy;
            if (child.getAge() < Constants.MAX_AGE_BABY) {
                childStrategy = ChildStrategyFactory.createStrategy(
                        ChildStrategyFactory.ChildType.Baby);
                child.setNiceScoreAverage(childStrategy.getAverageScore(child));
            }
            if (child.getAge() >= Constants.MAX_AGE_BABY
                    && child.getAge() < Constants.MAX_AGE_KID) {
                childStrategy = ChildStrategyFactory.createStrategy(
                        ChildStrategyFactory.ChildType.Kid);
                child.setNiceScoreAverage(childStrategy.getAverageScore(child));
            }
            if (child.getAge() >= Constants.MAX_AGE_KID
                    && child.getAge() < Constants.MIN_ADULTS) {
                childStrategy = ChildStrategyFactory.createStrategy(
                        ChildStrategyFactory.ChildType.Teen);
                child.setNiceScoreAverage(childStrategy.getAverageScore(child));
            }
        }
    }
    /**
     * method to assign the gifts
     */
    public void giftsAssignee() {
        for (Child child : Repository.getInstance().getChildrenData()) {
            double sum = 0;
            for (Category gift : child.getGiftsPreferences()) {
                Gift minValueGift = null;
                for (Gift santaGift : Repository.getInstance().getGiftsData()) {
                    if (santaGift.getCategory().equals(gift)) {
                        if (minValueGift == null
                                && santaGift.getQuantity() > 0) {
                            minValueGift = santaGift;
                            continue;
                        }
                        if (minValueGift == null) {
                            continue;
                        }
                        if (minValueGift.getPrice() > santaGift.getPrice()
                                && santaGift.getQuantity() > 0) {
                            minValueGift = santaGift;
                        }
                    }
                }
                if (minValueGift == null) {
                    continue;
                }
                sum = sum + minValueGift.getPrice();
                if (sum <= child.getAssignedBudget() && minValueGift.getQuantity() > 0) {
                    child.getReceivedGifts().add(minValueGift);
                    minValueGift.setQuantity(minValueGift.getQuantity() - 1);
                    continue;
                }
                sum = sum - minValueGift.getPrice();
            }
        }
    }
    /**
     * method for the rest of the rounds
     * @param output
     * the argument used to put the output for the rest rounds in the output object
     */
    public void allRounds(final Output output) {
        int years = 0;
        for (AnnualChanges annualChanges : Repository.getInstance().getAnnualChangesData()) {
            if (Repository.getInstance().getNumberYears() == years) {
                break;
            }
            years++;
            for (Child child : Repository.getInstance().getChildrenData()) {
                child.setAge(child.getAge() + 1);
            }
            //this.updateRepository(annualChanges);
            Repository.getInstance().updateRepository(annualChanges);
            this.removeAdults();
            this.scoreCalculator();

            double sum = 0;
            for (Child child : Repository.getInstance().getChildrenData()) {
                sum = sum + child.getNiceScoreAverage();
            }
            Repository.getInstance().setBugdetUnit(Repository.getInstance().getBudget() / sum);

            for (Child child : Repository.getInstance().getChildrenData()) {
                child.setAssignedBudget(child.getNiceScoreAverage()
                        * Repository.getInstance().getBugdetUnit());
                ElfStrategy elfStrategy = ElfStrategyFactory.createStrategy(child.getElf());
                child.setAssignedBudget(elfStrategy.getNewBudget(child));
            }

            AssignStrategy assignStrategy = AssignStrategyFactory.createStrategy(
                   annualChanges.getStrategy());
            assignStrategy.getOrder();

            this.giftsAssignee();

            this.yellowElf();

            assignStrategy = AssignStrategyFactory.createStrategy(CityStrategyEnum.ID);
            assignStrategy.getOrder();

            AnnualChildren annualChildren = new AnnualChildren();
            annualChildren.addChildren();
            output.getAnnualChildren().add(years, annualChildren);
        }
    }
    /**
     * method to assign the gifts for children that have yellow elf
     */
    public void yellowElf() {
        for (Child child : Repository.getInstance().getChildrenData()) {
            if (child.getElf().equals(ElvesType.YELLOW)
                    && child.getReceivedGifts().isEmpty()) {
                Gift minValueGift = null;
                for (Gift gift : Repository.getInstance().getGiftsData()) {
                    if (gift.getCategory().equals(child.getGiftsPreferences().get(0))) {
                        if (minValueGift == null) {
                            minValueGift = gift;
                            continue;
                        }
                        if (minValueGift.getPrice() > gift.getPrice()) {
                            minValueGift = gift;
                        }
                    }
                }
                if (minValueGift != null && minValueGift.getQuantity() > 0) {
                    child.getReceivedGifts().add(minValueGift);
                    minValueGift.setQuantity(minValueGift.getQuantity() - 1);
                }
            }
        }
    }
}


