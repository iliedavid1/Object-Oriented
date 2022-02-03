package entities;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private String name;

    private final String careerDescription;

    private ArrayList<String> filmography;

    private Map<ActorsAwards, Integer> awards;

    public Actor(final ActorInputData actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getCareerDescription() {
        return careerDescription;
    }

    public final ArrayList<String> getFilmography() {
        return filmography;
    }

    public final void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public final Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public final void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }
    /**
     * Calculate the total number of awards
     * @return the number of awards that an actor has
     */
    public final int numberAwards() {
        int noAwards = 0;
        for (Map.Entry<ActorsAwards, Integer> values : this.awards.entrySet()) {
            noAwards = noAwards + values.getValue();
        }
        return noAwards;
    }
}
