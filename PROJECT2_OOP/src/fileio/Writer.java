package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class Writer {
    /**
     * method to write all the data in out files
     * @param output
     * the arguments are used to get the filepath and to
     * get from output object the actual output
     */
    public void writeData(final String filepath, final Output output) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(new File(filepath), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
