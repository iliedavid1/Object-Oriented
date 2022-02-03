package fileio;

import database.Repository;
import entities.Child;

import java.util.ArrayList;

public class AnnualChildren {
    private ArrayList<ChildOutput> children =
            new ArrayList<>(Repository.getInstance().getNumberYears());
    /**
     * getter for children
     */
    public ArrayList<ChildOutput> getChildren() {
        return children;
    }
    /**
     * setter for children
     * @param children
     * the argument used to set the children with it
     */
    public void setChildren(final ArrayList<ChildOutput> children) {
        this.children = children;
    }
    /**
     * method for adding children from repository in children arrayList
     */
    public void addChildren() {
        for (Child child : Repository.getInstance().getChildrenData()) {
            ChildOutput childOutput = new ChildOutput(child);
            this.children.add(childOutput);
        }
    }
}
