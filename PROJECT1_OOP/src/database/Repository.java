package database;

import entities.Actor;
import entities.Movie;
import entities.Show;
import entities.User;
import fileio.*;


import java.util.ArrayList;
import java.util.List;

public final class Repository {

    private ArrayList<Actor> actorsData;

    private ArrayList<User> userData;

    private ArrayList<Movie> moviesData;

    private ArrayList<Show> showsData;

    private ArrayList<ActionInputData> actionData;

    private final static Repository instance = new Repository();

    private Repository() { }

    public static Repository getInstance() {
        return  instance;
    }
    /**
     * Add actors in database
     * @param actors from the input
     */
    public void addActors(final List<ActorInputData> actors) {
        this.actorsData = new ArrayList<>();
        for (ActorInputData actor : actors) {
            Actor actor1 = new Actor(actor);
            this.actorsData.add(actor1);
        }
    }
    /**
     * Add users in database
     * @param users from the input
     */
    public void addUsers(final List<UserInputData> users) {
        this.userData = new ArrayList<>();
        for (UserInputData user : users) {
            User user1 = new User(user);
            this.userData.add(user1);
        }
    }
    /**
     * Add movies in database
     * @param movies from the input
     */
    public void addMovies(final List<MovieInputData> movies) {
        this.moviesData = new ArrayList<>();
        for (MovieInputData movie : movies) {
            Movie movie1 = new Movie(movie);
            this.moviesData.add(movie1);
        }
    }
    /**
     * Add shows in database
     * @param shows from the input
     */
    public void addShows(final List<SerialInputData> shows) {
        this.showsData = new ArrayList<>();
        for (SerialInputData show : shows) {
            Show show1 = new Show(show);
            this.showsData.add(show1);
        }
    }
    /**
     * Add actions in database
     * @param actions from the input
     */
    public void addActions(final List<ActionInputData> actions) {
        this.actionData = new ArrayList<>();
        this.actionData.addAll(actions);
    }

    public ArrayList<Actor> getActorsData() {
        return actorsData;
    }

    public ArrayList<User> getUserData() {
        return userData;
    }

    public ArrayList<Movie> getMoviesData() {
        return moviesData;
    }

    public ArrayList<Show> getShowsData() {
        return showsData;
    }

    public ArrayList<ActionInputData> getActionData() {
        return actionData;
    }
}
