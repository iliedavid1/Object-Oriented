package fileio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Repository;
import entities.Child;
import entities.Gift;
import updates.AnnualChanges;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InputLoader {
    /**
     * method to read all the data from json
     * @param filepath
     * the argument used to get the data from the file
     */
    public void readData(final String filepath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode json = mapper.readTree(new File(filepath));
            Repository.getInstance().setNumberYears(json.get("numberOfYears").asInt());
            Repository.getInstance().setBudget(json.get("santaBudget").asDouble());
            JsonNode initialData = json.get("initialData");
            Repository.getInstance().setChildrenData(mapper.readValue(
                    initialData.get("children").toString(), new TypeReference<ArrayList<Child>>() {
                    }));
            Repository.getInstance().setGiftsData(mapper.readValue(
                    initialData.get("santaGiftsList").toString(),
                    new TypeReference<ArrayList<Gift>>() { }));
            Repository.getInstance().setAnnualChangesData(mapper.readValue(
                    json.get("annualChanges").toString(),
                    new TypeReference<ArrayList<AnnualChanges>>() { }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
