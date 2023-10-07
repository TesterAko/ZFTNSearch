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

    public static void tnSucheMaterialNr() throws FileNotFoundException {
        try (FileReader fileReader = new FileReader("src/main/resources/Tarifnummerliste.json");//liest die Daten aus der admin.json Datei
             Scanner scanner = new Scanner(fileReader);//erstellt einen Scanner mit dem FileReader
             Scanner scannerSearch = new Scanner(System.in)) {

            StringBuffer jsonContent = new StringBuffer();//StringBuffer erstellen aus jsonContent
            while (scanner.hasNext()) {//solange scanner einen nächsten String hat
                jsonContent.append(scanner.nextLine());//wird der String in jsonContent hinzugefügt
            }

            JSONArray json = new JSONArray(jsonContent.toString());//erstellt ein JSONArray aus dem jsonContent
            JSONObject result = null;

            Pattern searchPattern = Pattern.compile("\\s*(\\d+)\\s*");
            System.out.println("Suche nach Materialnummern");

            Integer inputNumber = null;
            inputNumber = scannerSearch.nextInt();
            Matcher opMatcher = searchPattern.matcher(inputNumber.toString());

            if (!opMatcher.matches()) {
                System.out.println("Falsche Eingabe");
                return;
            }
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
                /**
                 * dieser Block muss außerhalb der oberen if Schleife stehen,
                 *  um sicherzustellen, dass die Ausgabe "Materialnummer gefunden" oder
                 *  "Materialnummer nicht gefunden" erst nach dem Durchlaufen der gesamten Schleife erfolgt
                 */
                System.out.println("Ware gefunden");
                System.out.println("Materialnummer: " + result.getLong("Material"));
                System.out.println("Tarifnummer: " + result.getLong("EZT"));
                System.out.println("Materialkurztext: " + result.getString("Materialkurztext"));
                System.out.println("Text: " + result.getString("Übersetzung"));

                //Tarifnummerzahl für Int zu lang brauchen wir Long!!

            } else {
                System.out.println("Materialnummer nicht gefunden");
            }
            scanner.close();
            scannerSearch.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}






