package actions;

import database.Repository;
import entertainment.Genre;
import entities.Movie;
import entities.Show;
import entities.User;
import fileio.ActionInputData;
import utils.Utils;

import java.util.*;

public class Recommend {

    private final int actionId;

    private final String actionType;

    private final String type;

    private final String username;

    private final String genre;

    public Recommend(final ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.type = action.getType();
        this.username = action.getUsername();
        this.genre = action.getGenre();
    }

    public final int getActionId() {
        return actionId;
    }

    public final String getActionType() {
        return actionType;
    }

    public final String getType() {
        return type;
    }

    public final String getUsername() {
        return username;
    }

    public final String getGenre() {
        return genre;
    }
    /**
     * Search for a video to recommend to an user
     * @return a string that contains the video
     */
    public final String standardRecommend() {
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.username)) {
                for (Movie movie : Repository.getInstance().getMoviesData()) {
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        return "StandardRecommendation result: " + movie.getTitle();
                    }
                }
                for (Show show : Repository.getInstance().getShowsData()) {
                    if (!user.getHistory().containsKey(show.getTitle())) {
                        return "StandardRecommendation result: " + show.getTitle();
                    }
                }
            }
        }
        return "StandardRecommendation cannot be applied!";
    }
    /**
     * Search for a video that has the best rating
     * to recommend to an user
     * @return a string that contains the video
     */
    public final String bestUnseen() {
        LinkedHashMap<String, Double> videos = new LinkedHashMap<>();
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.username)) {
                for (Movie movie : Repository.getInstance().getMoviesData()) {
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        videos.put(movie.getTitle(), movie.movieRating());
                    }
                }
                for (Show show : Repository.getInstance().getShowsData()) {
                    if (!user.getHistory().containsKey(show.getTitle())) {
                        videos.put(show.getTitle(), show.ratingShow());
                    }
                }
            }
        }

        List<Map.Entry<String, Double>> list
                = new LinkedList<>(videos.entrySet());
        list.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
        for (Map.Entry<String, Double> entry : list) {
            return "BestRatedUnseenRecommendation result: " + entry.getKey();
        }

        return "BestRatedUnseenRecommendation cannot be applied!";
    }
    /**
     * Search for a video that appears the most in the favorite lists
     * to recommend to an user
     * @return a string that contains the video
     */
    public final String favoriteRecommend() {
        LinkedHashMap<String, Integer> videos = new LinkedHashMap<>();
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.username)) {
                if (user.getSubscriptionType().equals("BASIC")) {
                    return "FavoriteRecommendation cannot be applied!";
                }
                for (Movie movie : Repository.getInstance().getMoviesData()) {
                    if (!user.getHistory().containsKey(movie.getTitle())
                        && movie.numberFavorite() != 0) {
                        videos.put(movie.getTitle(), movie.numberFavorite());
                    }

                }
                for (Show show : Repository.getInstance().getShowsData()) {
                    if (!user.getHistory().containsKey(show.getTitle())
                        && show.numberFavorite() != 0) {
                        videos.put(show.getTitle(), show.numberFavorite());
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> list
                = new LinkedList<>(videos.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<String, Integer> entry : list) {
            return "FavoriteRecommendation result: " + entry.getKey();
        }

        return "FavoriteRecommendation cannot be applied!";
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
    /**
     * Search for the most popular genres
     * @return a list of genres
     */
    public  final List<Map.Entry<String, Integer>> popularGenres() {
        LinkedHashMap<String, Integer> genres = new LinkedHashMap<>();
        for (Genre genre : Genre.values()) {
            int nr = 0;
            for (Movie movie : Repository.getInstance().getMoviesData()) {
                if (movie.getGenres().contains(Utils.genreToString(genre))) {
                    nr = nr + movie.numberViews();
                }
            }
            for (Show show : Repository.getInstance().getShowsData()) {
                if (show.getGenres().contains(Utils.genreToString(genre))) {
                    nr = nr + show.numberViews();
                }
            }
            genres.put(Utils.genreToString(genre), nr);
        }
        List<Map.Entry<String, Integer>> list
                = new LinkedList<>(genres.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

        return list;
    }
    /**
     * Search for a video in specific genres
     * @return a string that contains the video
     */
    public final String searchRecommend() {
        StringBuilder answer = new StringBuilder("SearchRecommendation result: [");
        Map<String, Double> videos = new HashMap<>();
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.getUsername())) {
                if (user.getSubscriptionType().equals("BASIC")
                        || Utils.stringToGenre(this.getGenre()) == null) {
                    return "SearchRecommendation cannot be applied!";
                }
                for (Movie movie : Repository.getInstance().getMoviesData()) {
                    if (!user.getHistory().containsKey(movie.getTitle())
                            && movie.getGenres().contains(this.getGenre())) {
                        videos.put(movie.getTitle(), movie.movieRating());
                    }

                }
                for (Show show : Repository.getInstance().getShowsData()) {
                    if (!user.getHistory().containsKey(show.getTitle())
                            && show.getGenres().contains(this.getGenre())) {
                        videos.put(show.getTitle(), show.ratingShow());
                    }
                }
            }
        }
        List<Map.Entry<String, Double>> list
                = new LinkedList<>(videos.entrySet());
        if (list.isEmpty()) {
            return "SearchRecommendation cannot be applied!";
        }
        list.sort(Comparator.comparingDouble(Map.Entry::getValue));
        list.sort(Map.Entry.comparingByKey());
        int k = 0;
        for (Map.Entry<String, Double> entry : list) {
            k = 1;
            answer.append(entry.getKey());
            answer.append(", ");
        }
        if (k == 1) {
            answer = new StringBuilder(this.removeLast2Char(answer.toString()));
        }
        return answer + "]";
    }
    /**
     * Search for most popular video from a specific genre
     * @return a string that contains the video
     */
    public final String popularRecommend() {
        List<Map.Entry<String, Integer>> genres = this.popularGenres();
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.getUsername())) {
                if (user.getSubscriptionType().equals("BASIC")) {
                    return "PopularRecommendation cannot be applied!";
                }
                for (Map.Entry<String, Integer> genre : genres) {
                    for (Movie movie : Repository.getInstance().getMoviesData()) {
                        if (!user.getHistory().containsKey(movie.getTitle())
                                && movie.getGenres().contains(genre.getKey())) {
                            return "PopularRecommendation result: " + movie.getTitle();
                        }
                    }
                    for (Show show : Repository.getInstance().getShowsData()) {
                        if (!user.getHistory().containsKey(show.getTitle())
                                && show.getGenres().contains(genre.getKey())) {
                            return "PopularRecommendation result: " + show.getTitle();
                        }
                    }
                }
            }
        }
        return "PopularRecommendation cannot be applied!";
    }
}
