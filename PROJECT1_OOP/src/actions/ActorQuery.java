package actions;

import database.Repository;
import entities.Actor;
import entities.Movie;
import entities.Show;
import fileio.ActionInputData;
import utils.Utils;

import java.util.*;

public class ActorQuery extends Query {
    private final List<List<String>> filters;

    public ActorQuery(final ActionInputData action) {
        super(action);
        this.filters = action.getFilters();
    }

    public final List<List<String>> getFilters() {
        return this.filters;
    }
    /**
     * Get rating of an actor
     * @param actor from Actor list
     * @return rating of actor (double)
     */
    public final double ratingActor(final Actor actor) {
        double rating = 0;
        int k = 0;
        for (String videos : actor.getFilmography()) {
            for (Movie movie : Repository.getInstance().getMoviesData()) {
                if (movie.getTitle().equals(videos) && movie.movieRating() != 0) {
                    rating = rating + movie.movieRating();
                    k++;
                }
            }
            for (Show show : Repository.getInstance().getShowsData()) {
                if (show.getTitle().equals(videos) && show.ratingShow() != 0) {
                    rating = rating + show.ratingShow();
                    k++;
                }
            }
        }
        if (actor.getFilmography().size() == 0 || rating == 0) {
            return 0;
        }
        return rating / k;
    }
    /**
     * Get a list of actors based on rating
     * @return a string of actors
     */
    public final String actorAverage() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Double> actors = new HashMap<>();
        for (Actor actor : Repository.getInstance().getActorsData()) {
            actors.put(actor.getName(), this.ratingActor(actor));
        }
        actors = this.sortByKey(actors);
        LinkedHashMap<String, Double> sortMap = this.sortMapDouble(actors);

        int k = 0;
        Set<String> setKeys = sortMap.keySet();
        for (String key : setKeys) {
            if (k == this.getNumber()) {
                break;
            }
            if (sortMap.get(key) == 0 || Double.isNaN(sortMap.get(key))) {
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
     * Get a list of actors based on awards
     * @return a string of actors
     */
    public final String actorAwards() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> actors = new HashMap<>();
        for (Actor actor : Repository.getInstance().getActorsData()) {
            int ok = 0;
            for (String awards : this.filters.get(3)) {
                if (!actor.getAwards().containsKey(Utils.stringToAwards(awards))) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                actors.put(actor.getName(), actor.numberAwards());
            }
        }

        actors = this.sortByKeyInteger(actors);
        LinkedHashMap<String, Integer> sortMap = this.sortMap(actors);

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
     * Get a list of actors based on words
     * that are included in their description
     * @return a string of actors
     */
    public String actorDescription() {
        StringBuilder answer = new StringBuilder("Query result: [");
        List<String> actors = new ArrayList<>();
        for (Actor actor : Repository.getInstance().getActorsData()) {
            int ok = 0;
            for (String words : this.filters.get(2)) {
                if (!actor.getCareerDescription().toLowerCase().contains(words.toLowerCase() + " ")
                && !actor.getCareerDescription().toLowerCase().contains(words.toLowerCase() + ".")
                && !actor.getCareerDescription().toLowerCase().contains(words.toLowerCase()
                        + ",")) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                actors.add(actor.getName());
            }
        }
        Collections.sort(actors);
        if (this.getSortType().equals("desc")) {
            Collections.reverse(actors);
        }

        int k = 0;
        for (String key : actors) {
            if (k == this.getNumber()) {
                break;
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
