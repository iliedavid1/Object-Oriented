package entities;

import entertainment.Season;
import fileio.SerialInputData;
import fileio.ShowInput;

import java.util.ArrayList;

public class Show extends Video {
    private int numberOfSeasons;

    private ArrayList<Season> seasons;

    public Show() { }

    public Show(final ShowInput show, final int numberOfSeasons,
                final ArrayList<Season> seasons) {
        super(show);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public Show(final SerialInputData show) {
        this.numberOfSeasons = show.getNumberSeason();
        this.seasons = show.getSeasons();
        this.setTitle(show.getTitle());
        this.setYear(show.getYear());
        this.setCast(show.getCast());
        this.setGenres(show.getGenres());
    }

    public final ArrayList<Season> getSeasons() {
        return seasons;
    }

    public final void setSeasons(final ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
    /**
     * Calculate the total rating of a show
     * @return the rating
     */
    public final double ratingShow() {
        double sum = 0;
        for (Season season : this.getSeasons()) {
            sum = sum + season.ratingSeason();
        }
        if (Double.isNaN(sum / this.numberOfSeasons)) {
            return 0;
        }
        return sum / this.numberOfSeasons;
    }
    /**
     * Calculate the total duration of a show
     * @return duration
     */
    public final int durationShow() {
        int sum = 0;
        for (Season season : this.getSeasons()) {
            sum = sum + season.getDuration();
        }

        return sum;
    }
}
