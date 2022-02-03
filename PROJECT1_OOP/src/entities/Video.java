package entities;

import database.Repository;
import fileio.ShowInput;

import java.util.ArrayList;

public abstract class Video {
    private String title;

    private int year;

    private ArrayList<String> cast;

    private ArrayList<String> genres;

    public Video() { }

    public Video(final ShowInput video) {
        this.title = video.getTitle();
        this.year = video.getYear();
        this.cast = video.getCast();
        this.genres = video.getGenres();
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(final String title) {
        this.title = title;
    }

    public final int getYear() {
        return year;
    }

    public final void setYear(final int year) {
        this.year = year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final void setCast(final ArrayList<String> cast) {
        this.cast = cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }
    /**
     * Calculate the number of appearances in favorite lists
     * @return number of appearances
     */
    public final int numberFavorite() {
        int noFavorite = 0;
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getFavoriteMovies().contains(this.title)) {
                noFavorite++;
            }
        }
        return noFavorite;
    }
    /**
     * Calculate the number of views of a video
     * @return number of views
     */
    public final int numberViews() {
        int noViews = 0;
        for (User user : Repository.getInstance().getUserData()) {
            if (user.getHistory().containsKey(this.title)) {
                noViews = noViews + user.getHistory().get(this.title);
            }
        }
        return noViews;
    }
}
