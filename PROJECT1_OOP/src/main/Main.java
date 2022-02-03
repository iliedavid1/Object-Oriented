package main;

import actions.*;
import database.Repository;
import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation

        Repository.getInstance().addActors(input.getActors());
        Repository.getInstance().addUsers(input.getUsers());
        Repository.getInstance().addMovies(input.getMovies());
        Repository.getInstance().addShows(input.getSerials());
        Repository.getInstance().addActions(input.getCommands());

        for (ActionInputData action : Repository.getInstance().getActionData()) {
            if (action.getActionType().equals(Constants.COMMAND)) {
                Command command = new Command(action);
                if (command.getType().equals("favorite")) {
                    //System.out.println("ceva");
                    arrayResult.add(fileWriter.writeFile(command.getActionId(),
                            "", command.favoriteCommand()));
                }
                if (command.getType().equals("view")) {
                    arrayResult.add(fileWriter.writeFile(command.getActionId(),
                            "", command.viewCommand()));
                }
                if (command.getType().equals("rating")) {
                    if (command.getSeasonNumber() == 0) {
                        arrayResult.add(fileWriter.writeFile(command.getActionId(),
                                "", command.ratingMovie()));
                    } else {
                        arrayResult.add(fileWriter.writeFile(command.getActionId(),
                                "", command.ratingShow()));
                    }
                }
            }
            if (action.getActionType().equals(Constants.QUERY)) {
                if (action.getObjectType().equals(Constants.USERS)) {
                    UserQuery userQuery = new UserQuery(action);
                    arrayResult.add(fileWriter.writeFile(userQuery.getActionId(),
                            "", userQuery.userQuery()));
                }
                if (action.getObjectType().equals(Constants.MOVIES)) {
                    MovieQuery movieQuery = new MovieQuery(action);
                    if (movieQuery.getCriteria().equals("longest")) {
                        arrayResult.add(fileWriter.writeFile(movieQuery.getActionId(),
                                "", movieQuery.videoQueryLongest()));
                    }
                    if (movieQuery.getCriteria().equals("favorite")) {
                        arrayResult.add(fileWriter.writeFile(movieQuery.getActionId(),
                                "", movieQuery.movieFavorite()));
                    }
                    if (movieQuery.getCriteria().equals("most_viewed")) {
                        arrayResult.add(fileWriter.writeFile(movieQuery.getActionId(),
                                "", movieQuery.movieViews()));
                    }
                    if (movieQuery.getCriteria().equals("ratings")) {
                        arrayResult.add(fileWriter.writeFile(movieQuery.getActionId(),
                                "", movieQuery.movieBestRated()));
                    }
                }
                if (action.getObjectType().equals(Constants.SHOWS)) {
                    ShowQuery showQuery = new ShowQuery(action);
                    if (showQuery.getCriteria().equals("longest")) {
                        arrayResult.add(fileWriter.writeFile(showQuery.getActionId(),
                                "", showQuery.videoQueryLongest()));
                    }
                    if (showQuery.getCriteria().equals("favorite")) {
                        arrayResult.add(fileWriter.writeFile(showQuery.getActionId(),
                                "", showQuery.showFavorite()));
                    }
                    if (showQuery.getCriteria().equals("most_viewed")) {
                        arrayResult.add(fileWriter.writeFile(showQuery.getActionId(),
                                "", showQuery.showViews()));
                    }
                    if (showQuery.getCriteria().equals("ratings")) {
                        arrayResult.add(fileWriter.writeFile(showQuery.getActionId(),
                                "", showQuery.showBestRated()));
                    }
                }
                if (action.getObjectType().equals(Constants.ACTORS)) {
                    ActorQuery actorQuery = new ActorQuery(action);
                    if (actorQuery.getCriteria().equals("average")) {
                        arrayResult.add(fileWriter.writeFile(actorQuery.getActionId(),
                                "", actorQuery.actorAverage()));
                    }
                    if (actorQuery.getCriteria().equals("awards")) {
                        arrayResult.add(fileWriter.writeFile(actorQuery.getActionId(),
                                "", actorQuery.actorAwards()));
                    }
                    if (actorQuery.getCriteria().equals("filter_description")) {
                        arrayResult.add(fileWriter.writeFile(actorQuery.getActionId(),
                                "", actorQuery.actorDescription()));
                    }
                }
            }
            if (action.getActionType().equals(Constants.RECOMMENDATION)) {
                Recommend recommend = new Recommend(action);
                if (recommend.getType().equals("standard")) {
                    arrayResult.add(fileWriter.writeFile(recommend.getActionId(),
                            "", recommend.standardRecommend()));
                }
                if (recommend.getType().equals("best_unseen")) {
                    arrayResult.add(fileWriter.writeFile(recommend.getActionId(),
                            "", recommend.bestUnseen()));
                }
                if (recommend.getType().equals("favorite")) {
                    arrayResult.add(fileWriter.writeFile(recommend.getActionId(),
                            "", recommend.favoriteRecommend()));
                }
                if (recommend.getType().equals("search")) {
                    arrayResult.add(fileWriter.writeFile(recommend.getActionId(),
                            "", recommend.searchRecommend()));
                }
                if (recommend.getType().equals("popular")) {
                    arrayResult.add(fileWriter.writeFile(recommend.getActionId(),
                            "", recommend.popularRecommend()));
                }
            }
        }

        fileWriter.closeJSON(arrayResult);
    }
}
