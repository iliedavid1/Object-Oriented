package actions;

import database.Repository;
import entities.Movie;
import fileio.ActionInputData;

import java.util.*;

public class MovieQuery extends Query {
    private final List<List<String>> filters;

    public MovieQuery(final ActionInputData action) {
        super(action);
        this.filters = action.getFilters();
    }

    public final List<List<String>> getFilters() {
        return filters;
    }
    /**
     * Search for the longest movies
     * @return a string that shows a list of movies
     */
    public final String videoQueryLongest() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> videos = new HashMap<>();
        for (Movie movie : Repository.getInstance().getMoviesData()) {
            if (this.getFilters().get(0).contains(Integer.toString(movie.getYear()))
            || this.getFilters().get(0).get(0) == null) {
                if (this.getFilters().get(1).get(0) == null) {
                    videos.put(movie.getTitle(), movie.getDuration());
                }
                for (String genre : this.getFilters().get(1)) {
                    if (movie.getGenres().contains(genre)) {
                        videos.put(movie.getTitle(), movie.getDuration());
                    }
                }
            }
        }
        videos = this.sortByKeyInteger(videos);
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
     * Search for movies that appear the most in favorite lists
     * @return a string that shows a list of movies
     */
    public final String movieFavorite() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> movies = new HashMap<>();
        for (Movie movie : Repository.getInstance().getMoviesData()) {
            if (this.getFilters().get(0).contains(Integer.toString(movie.getYear()))
                || this.getFilters().get(0).get(0) == null) {
                if (this.getFilters().get(1).get(0) == null) {
                    movies.put(movie.getTitle(), movie.numberFavorite());
                    continue;
                }
                for (String genre : this.getFilters().get(1)) {
                    if (movie.getGenres().contains(genre)) {
                        movies.put(movie.getTitle(), movie.numberFavorite());
                    }
                }
            }
        }
        movies = this.sortByKeyInteger(movies);
        LinkedHashMap<String, Integer> sortMap = this.sortMap(movies);

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
     * Search for the movies that have been viewed the most
     * @return a string that shows a list of movies
     */
    public final String movieViews() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> movies = new HashMap<>();
        for (Movie movie : Repository.getInstance().getMoviesData()) {
            if (this.getFilters().get(0).contains(Integer.toString(movie.getYear()))
            || this.getFilters().get(0).get(0) == null) {
                if (this.getFilters().get(1).get(0) == null) {
                    movies.put(movie.getTitle(), movie.numberViews());
                }
                for (String genre : this.getFilters().get(1)) {
                    if (movie.getGenres().contains(genre)) {
                        movies.put(movie.getTitle(), movie.numberViews());
                    }
                }
            }
        }
        movies = this.sortByKeyInteger(movies);
        LinkedHashMap<String, Integer> sortMap = this.sortMap(movies);

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
     * Search for the best rated movies
     * @return a string that shows a list of movies
     */
    public final String movieBestRated() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Double> movies = new HashMap<>();
        for (Movie movie : Repository.getInstance().getMoviesData()) {
            if (this.getFilters().get(0).contains(Integer.toString(movie.getYear()))) {
                for (String genre : this.getFilters().get(1)) {
                    if (movie.getGenres().contains(genre)) {
                        movies.put(movie.getTitle(), movie.movieRating());
                    }
                }
            }
        }

        LinkedHashMap<String, Double> sortMap = this.sortMapDouble(movies);

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
