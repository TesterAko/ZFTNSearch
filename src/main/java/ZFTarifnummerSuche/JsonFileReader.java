package ZFTarifnummerSuche;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class JsonFileReader {
    private static JsonFileReader INSTANCE;
    private final Scanner scanner;

    public static JsonFileReader getInstance() {
        // only for single thread
        if (INSTANCE == null) {
            INSTANCE = new JsonFileReader();
        }
        return INSTANCE;
    }

    private JsonFileReader() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/Tarifnummerliste.json");
            this.scanner = new Scanner(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Please check if feile exists, we have an error: " + e.getMessage());
        }
    }

    public JSONArray readrJsonFile() {
        StringBuilder jsonContent = new StringBuilder();
        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        return new JSONArray(jsonContent.toString());
    }

    public void close(){
        scanner.close();
    }
}
