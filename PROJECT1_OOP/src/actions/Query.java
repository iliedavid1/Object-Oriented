package actions;

import fileio.ActionInputData;

import java.util.*;

public abstract class Query {

    private int actionId;

    private String actionType;

    private int number;

    private String sortType;

    private String criteria;

    public Query() { }

    public Query(final ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.number = action.getNumber();
        this.sortType = action.getSortType();
        this.criteria = action.getCriteria();
    }

    public final int getActionId() {
        return actionId;
    }

    public final String getActionType() {
        return actionType;
    }

    public final int getNumber() {
        return number;
    }

    public final String getSortType() {
        return sortType;
    }

    public final String getCriteria() {
        return criteria;
    }
    /**
     * Sort a map based on sortType
     * @param map - a map of string, integer to sort
     * @return a LinkedHashMap sorted correctly
     */
    public final LinkedHashMap<String, Integer> sortMap(final Map<String, Integer> map) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        if (this.getSortType().equals("asc")) {
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        } else {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        }

        return sortedMap;
    }
    /**
     * Sort a map based on sortType
     * @param map - a map of string, double to sort
     * @return a LinkedHashMap sorted correctly
     */
    public final LinkedHashMap<String, Double> sortMapDouble(final Map<String, Double> map) {
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();

        if (this.getSortType().equals("asc")) {
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        } else {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        }

        return sortedMap;
    }
    /**
     * Sort a map based on sortType, but by keys
     * @param hm - a map of string, double to sort
     * @return a LinkedHashMap sorted correctly
     */
    public final Map<String, Double> sortByKey(final Map<String, Double> hm) {
        List<Map.Entry<String, Double>> list
                = new LinkedList<>(
                hm.entrySet());

        list.sort(Map.Entry.comparingByKey());
        if (this.getSortType().equals("desc")) {
            Collections.reverse(list);
        }
        HashMap<String, Double> temp
                = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Sort a map based on sortType, but by keys
     * @param hm - a map of string, integer to sort
     * @return a LinkedHashMap sorted correctly
     */
    public final Map<String, Integer> sortByKeyInteger(final Map<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list
                = new LinkedList<>(
                hm.entrySet());
        list.sort(Map.Entry.comparingByKey());
        if (this.getSortType().equals("desc")) {
            Collections.reverse(list);
        }

        HashMap<String, Integer> temp
                = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Remove last two chars from a string
     * @param s a string
     * @return the string, but without the last 2 chars
     */
    public final String removeLast2Char(String s) {
        s = s.substring(0, s.length() - 1);
        return s.substring(0, s.length() - 1);
    }
}
