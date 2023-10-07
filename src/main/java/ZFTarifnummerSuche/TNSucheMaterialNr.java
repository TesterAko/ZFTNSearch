package ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TNSucheMaterialNr {//funktioniert

    public static void tnSucheMaterialNr() {
        InputReader inputReader = InputReader.getInstance();
        JsonFileReader fileReader = JsonFileReader.getInstance();
        JSONArray json = fileReader.readrJsonFile();

        int inputNumber = inputReader.readMaterialNumber();

        JSONObject result = null;

        for (int i = 0; i < json.length(); i++) {
            JSONObject empObject = json.getJSONObject(i);
            if (empObject.has("Material") && empObject.getInt("Material") == inputNumber) {
                result = empObject;
                break;
                /**
                 * davor:
                 * for (int i = 0; i < json.length(); i++) {
                 *     JSONObject empObject = json.getJSONObject(i);
                 *     if (empObject.has("Material")) {
                 *         if (empObject.getInt("Material") == inputNumber) {
                 *             result = empObject;
                 *             break;
                 * if (empObject.getInt("Material") == inputNumber) prüft nur, ob die Materialnummer übereinstimmt,
                 * aber sie sollte auch sicherstellen, dass das gefundene Element die korrekte Materialnummer hat
                 *
                 * AKTUELL:
                 *jetzt nicht nur die Materialnummer,
                 * sondern auch die zugehörige Tarifnummer, den Materialkurztext und den Text anzeigen
                 */
            }
        }
        if (result != null) {
            TerminalDecorator.printResult(result);
        } else {
            System.out.println("Materialnummer nicht gefunden");
        }
    }
}






