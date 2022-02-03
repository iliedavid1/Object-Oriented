package actions;

import database.Repository;
import entities.Show;
import fileio.ActionInputData;

import java.util.*;

public class ShowQuery extends Query {
    private final List<List<String>> filters;

    public ShowQuery(final ActionInputData action) {
        super(action);
        this.filters = action.getFilters();
    }

    public final List<List<String>> getFilters() {
        return filters;
    }
    /**
     * Search for the longest shows
     * @return a string that shows a list of shows
     */
    public final String videoQueryLongest() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> videos = new HashMap<>();

        for (Show show : Repository.getInstance().getShowsData()) {
            if (this.getFilters().get(0).contains(Integer.toString(show.getYear()))
            || this.getFilters().get(0).get(0) == null) {
                if (this.getFilters().get(1).get(0) == null) {
                    videos.put(show.getTitle(), show.durationShow());
                }
                for (String genre : this.getFilters().get(1)) {
                    if (show.getGenres().contains(genre)) {
                        videos.put(show.getTitle(), show.durationShow());
                    }
                }
            }
        }
        LinkedHashMap<String, Integer> sortMap = this.sortMap(videos);

        int k = 0;
        Set<String> setKeys = sortMap.keySet();
        for (String key : setKeys) {
            if (k == this.getNumber()) {
                break;
            }
            if (sortMap.get(key) == 0) {
                continue;
            }
            answer.append(key);
            answer.append(", ");
            k++;
        }
        if (k != 0) {
            answer = new StringBuilder(this.removeLast2Char(answer.toString()));
        }

        return answer + "]";
    }
    /**
     * Search for shows that appear the most in favorite lists
     * @return a string that shows a list of shows
     */
    public final String showFavorite() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> shows = new HashMap<>();
        for (Show show : Repository.getInstance().getShowsData()) {
            if (this.getFilters().get(0).contains(Integer.toString(show.getYear()))
            || this.getFilters().get(0).get(0) == null) {
                if (this.getFilters().get(1).get(0) == null) {
                    shows.put(show.getTitle(), show.numberFavorite());
                }
                for (String genre : this.getFilters().get(1)) {
                    if (show.getGenres().contains(genre)) {
                        shows.put(show.getTitle(), show.numberFavorite());
                    }
                }
            }
        }

        LinkedHashMap<String, Integer> sortMap = this.sortMap(shows);

        int k = 0;
        Set<String> setKeys = sortMap.keySet();
        for (String key : setKeys) {
            if (k == this.getNumber()) {
                break;
            }
            if (sortMap.get(key) == 0) {
                continue;
            }

            answer.append(key);
            answer.append(", ");
            k++;
        }
        if (k != 0) {
            answer = new StringBuilder(this.removeLast2Char(answer.toString()));
        }
        return answer + "]";
    }
    /**
     * Search for the shows that have been viewed the most
     * @return a string that shows a list of shows
     */
    public final String showViews() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> shows = new HashMap<>();
        for (Show show : Repository.getInstance().getShowsData()) {
            if (this.getFilters().get(0).contains(Integer.toString(show.getYear()))
            || this.getFilters().get(0).get(0) == null) {
                if (getFilters().get(1).get(0) == null) {
                    shows.put(show.getTitle(), show.numberViews());
                }
                for (String genre : this.getFilters().get(1)) {
                    if (show.getGenres().contains(genre)) {
                        shows.put(show.getTitle(), show.numberViews());
                    }
                }
            }
        }
        shows = this.sortByKeyInteger(shows);
        LinkedHashMap<String, Integer> sortMap = this.sortMap(shows);

        int k = 0;
        Set<String> setKeys = sortMap.keySet();
        for (String key : setKeys) {
            if (k == this.getNumber()) {
                break;
            }
            if (sortMap.get(key) == 0) {
                continue;
            }

            answer.append(key);
            answer.append(", ");
            k++;
        }
        if (k != 0) {
            answer = new StringBuilder(this.removeLast2Char(answer.toString()));
        }
        return answer + "]";
    }
    /**
     * Search for the best rated shows
     * @return a string that shows a list of shows
     */
    public final String showBestRated() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Double> shows = new HashMap<>();
        for (Show show : Repository.getInstance().getShowsData()) {
            if (this.getFilters().get(0).contains(Integer.toString(show.getYear()))
            || this.getFilters().get(0).get(0) == null) {
                if (this.getFilters().get(1).get(0) == null) {
                    shows.put(show.getTitle(), show.ratingShow());
                }
                for (String genre : this.getFilters().get(1)) {
                    if (show.getGenres().contains(genre)) {
                        shows.put(show.getTitle(), show.ratingShow());
                    }
                }
            }
        }

        LinkedHashMap<String, Double> sortMap = this.sortMapDouble(shows);

        int k = 0;
        Set<String> setKeys = sortMap.keySet();
        for (String key : setKeys) {
            if (k == this.getNumber()) {
                break;
            }
            if (sortMap.get(key) == 0) {
                continue;
            }

            answer.append(key);
            answer.append(", ");
            k++;
        }
        if (k != 0) {
            answer = new StringBuilder(this.removeLast2Char(answer.toString()));
        }
        return answer + "]";
    }
}
