package entities;

import fileio.MovieInputData;
import fileio.ShowInput;

import java.util.HashMap;
import java.util.Map;

public class Movie extends Video {
    private int duration;

    private Map<String, Double> ratings;

    public final Map<String, Double> getRatings() {
        return ratings;
    }

    public final void setRatings(final Map<String, Double> ratings) {
        this.ratings = ratings;
    }

    public Movie() { }

    public Movie(final ShowInput video, final int duration) {
        super(video);
        this.duration = duration;
    }

    public Movie(final MovieInputData movie) {
        this.duration = movie.getDuration();
        this.setTitle(movie.getTitle());
        this.setYear(movie.getYear());
        this.setCast(movie.getCast());
        this.setGenres(movie.getGenres());
        this.ratings = new HashMap<>();
    }

    public final int getDuration() {
        return duration;
    }

    public final void setDuration(final int duration) {
        this.duration = duration;
    }
    /**
     * Calculate the total rating of a movie
     * @return the rating
     */
    public final double movieRating() {
        double sum = 0;
        for (String key : this.getRatings().keySet()) {
            sum = sum + this.getRatings().get(key);
        }
        if (Double.isNaN(sum / this.getRatings().size())) {
            return 0;
        }
        return sum / this.getRatings().size();
    }
}
