package de.ekrem.ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;



public class TNSucheMaterialNr {//funktioniert

    public static void tnSucheMaterialNr() {
        InputReader inputReader = InputReader.getInstance();
        JsonFileReader fileReader = JsonFileReader.getInstance();
        JSONArray json = fileReader.readrJsonFile();

        long inputNumber = inputReader.readMaterialNumber();

        JSONObject resultText = null;

        for (int i = 0; i < json.length(); i++) {
            JSONObject empObject = json.getJSONObject(i);
            if (empObject.has("Material") && empObject.getInt("Material") == inputNumber) {
                resultText = empObject;
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
                 **/
            }
        }
        if (resultText != null) {
            TerminalDecorator.printResult(resultText);
        } else {
            System.out.println("Ware nicht gefunden");
        }
    }
}






