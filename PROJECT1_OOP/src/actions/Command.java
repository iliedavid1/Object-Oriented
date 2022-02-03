package actions;

import database.Repository;
import entities.Movie;
import entities.Show;
import entities.User;
import fileio.ActionInputData;

public class Command {

    private final int actionId;

    private final String actionType;

    private final String type;

    private final String username;

    private final String title;

    private final double grade;

    private final int seasonNumber;

    public Command(final ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.type = action.getType();
        this.username = action.getUsername();
        this.title = action.getTitle();
        this.grade = action.getGrade();
        this.seasonNumber = action.getSeasonNumber();
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

    public final String getTitle() {
        return title;
    }

    public final double getGrade() {
        return grade;
    }

    public final int getSeasonNumber() {
        return seasonNumber;
    }
    /**
     * Add a video to favorite list of an user
     * @return a string that shows if it worked or not
     */
    public final String favoriteCommand() {
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.username)) {
                if (!user.getHistory().containsKey(this.title)) {
                    return "error -> " + this.title + " is not seen";
                }

                if (user.getFavoriteMovies().contains(this.title)) {
                    return "error -> " + this.title + " is already in favourite list";
                }
                user.addFavorite(this.title);
                return "success -> " + this.title + " was added as favourite";
            }
        }
        return "error";
    }
    /**
     * Add a video to view list of an user
     * @return a string that shows if it worked or not
     */
    public final String viewCommand() {
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.username)) {
                if (user.getHistory().containsKey(this.title)) {
                    user.getHistory().merge(this.title, 1, Integer::sum);
                } else {
                    user.getHistory().put(this.title, 1);
                }
                return "success -> " + this.title + " was viewed with total views of "
                        + user.getHistory().get(this.title);
            }
        }
        return "error";
    }
    /**
     * User add a rating in rating map of a movie
     * @return a string that shows if it worked or not
     */
    public final String ratingMovie() {
        for (User user :Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.getUsername())) {
                if (!user.getHistory().containsKey(this.getTitle())) {
                    return "error -> " + this.title + " is not seen";
                }
            }
        }
        for (Movie movie : Repository.getInstance().getMoviesData()) {
            if (movie.getTitle().equals(this.getTitle())) {
                if (movie.getRatings().containsKey(this.getUsername())) {
                    return "error -> " + this.getTitle() + " has been already rated";
                } else {
                    movie.getRatings().put(this.getUsername(), this.getGrade());
                    for (User user : Repository.getInstance().getUserData()) {
                        if (user.getUsername().equals(this.getUsername())) {
                            user.setNumberRating(user.getNumberRating() + 1);
                        }
                    }
                    return "success -> " + this.title
                            + " was rated with " + this.grade + " by "
                            + this.username;
                }
            }
        }
        return "error";
    }
    /**
     * User add a rating in rating map of a season from a show
     * @return a string that shows if it worked or not
     */
    public final String ratingShow() {
        for (User user :Repository.getInstance().getUserData()) {
            if (user.getUsername().equals(this.username)) {
                if (!user.getHistory().containsKey(this.title)) {
                    return "error -> " + this.title + " is not seen";
                }
            }
        }
        for (Show show : Repository.getInstance().getShowsData()) {
            if (show.getTitle().equals(this.title)) {
                if (show.getSeasons().get(this.seasonNumber - 1)
                        .getRatings().containsKey(this.username)) {
                    return "error -> " + this.title + " has been already rated";
                } else {
                    show.getSeasons().get(this.seasonNumber - 1)
                            .getRatings().put(this.username, this.grade);
                    for (User user : Repository.getInstance().getUserData()) {
                        if (user.getUsername().equals(this.username)) {
                            user.setNumberRating(user.getNumberRating() + 1);
                        }
                    }
                    return "success -> " + this.title
                            + " was rated with " + this.grade + " by "
                            + this.username;
                }
            }
        }
        return "error";
    }
}
