package fileio;

import java.util.ArrayList;

public class Output {
    private ArrayList<AnnualChildren> annualChildren = new ArrayList<>();
    /**
     * getter for annualChildren
     */
    public ArrayList<AnnualChildren> getAnnualChildren() {
        return annualChildren;
    }
    /**
     * setter for annualChildren
     * @param annualChildren
     * the argument used to set the annualChildren with it
     */
    public void setAnnualChildren(final ArrayList<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
