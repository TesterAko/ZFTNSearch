package de.ekrem.ZFTarifnummerSuche;

import org.json.JSONArray;

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
            this.scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("Tarifnummerliste.json"));
        } catch (Exception e) {
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
