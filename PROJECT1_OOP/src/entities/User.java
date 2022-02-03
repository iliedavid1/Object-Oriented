package entities;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;

public class User {
    private final String username;

    private final String subscriptionType;

    private int numberRating;

    private final ArrayList<String> favoriteMovies;

    private final Map<String, Integer> history;

    public User(final UserInputData user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.favoriteMovies = user.getFavoriteMovies();
        this.history = user.getHistory();
    }
    public User(final User user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.favoriteMovies = user.getFavoriteMovies();
        this.history = user.getHistory();
        this.numberRating = user.getNumberRating();
    }

    public final String getUsername() {
        return username;
    }

    public final String getSubscriptionType() {
        return subscriptionType;
    }

    public final ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public final Map<String, Integer> getHistory() {
        return history;
    }
    /**
     * Add movies in favorite list
     * @param favorite the title of a movie
     */
    public final void addFavorite(final String favorite) {
        this.favoriteMovies.add(favorite);
    }

    public final int getNumberRating() {
        return numberRating;
    }

    public final void setNumberRating(final int numberRating) {
        this.numberRating = numberRating;
    }

}
