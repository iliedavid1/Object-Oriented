package main;

import checker.Checker;
import common.Constants;
import database.Repository;
import execution.Executor;
import execution.UpdateRepository;
import fileio.InputLoader;
import fileio.Output;
import fileio.Writer;

import java.io.File;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {

        File theDir = new File("output");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        UpdateRepository updateRepository = new UpdateRepository();
        Repository.getInstance().addObserver(updateRepository);

        for (int i = 1; i < Constants.MAX_TEST_INDEX; i++) {
            String filepath = "tests/test" + i + ".json";
            InputLoader inputLoader = new InputLoader();
            inputLoader.readData(filepath);

            Executor executor = new Executor();

            Output output = new Output();

            executor.firstRound(output);
            executor.allRounds(output);

            Writer writer = new Writer();
            String outputFile = "output/out_" + i + ".json";
            writer.writeData(outputFile, output);
            Repository.getInstance().clearAll();
        }


        Checker.calculateScore();
    }
}
