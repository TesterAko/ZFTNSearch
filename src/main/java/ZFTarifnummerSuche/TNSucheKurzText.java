package ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TNSucheKurzText {

    public static void tnSucheKurzText() throws FileNotFoundException {
        InputReader inputReader = InputReader.getInstance();
        JsonFileReader fileReader = JsonFileReader.getInstance();
        JSONArray json = fileReader.readrJsonFile();
        JSONObject result = null;

        Pattern searchPattern = Pattern.compile("\\s*([a-zA-Z]+)\\s*");
        String inputString = inputReader.readText().toUpperCase();
        Matcher opMatcher = searchPattern.matcher(inputString);
        if (!opMatcher.matches()) {
            System.out.println("Falsche Eingabe");
            return;
        }

        for (int i = 0; i < json.length(); i++) {
            JSONObject empObject = json.getJSONObject(i);
            if (empObject.has("Materialkurztext") && empObject.getString("Materialkurztext").equals(inputString)) {
                result = empObject;
                break;
            }
        }
        if (result != null) {
            TerminalDecorator.printResult(result);
        } else {
            System.out.println("Ware nicht gefunden");
        }
    }
}
/*
        try (FileReader fileReader = new FileReader("src/main/resources/Tarifnummerliste.json");//liest die Daten aus der admin.json Datei
             Scanner scanner = new Scanner(fileReader);//erstellt einen Scanner mit dem JsonFileReader
             Scanner scannerSearch = new Scanner(System.in)) {

            StringBuffer jsonContent = new StringBuffer();//StringBuffer erstellen aus jsonContent
            while (scanner.hasNext()) {//solange scanner einen nächsten String hat
                jsonContent.append(scanner.nextLine());//wird der String in jsonContent hinzugefügt
            }

            JSONArray json = new JSONArray(jsonContent.toString());//erstellt ein JSONArray aus dem jsonContent
            JSONObject result = null;

            Pattern searchPattern = Pattern.compile("\\s*([a-zA-Z]+)\\s*");
            //azAZ sucht nur eine einzige Buchstabe egal groß klein
            System.out.println("Suche nach Kurztext");

            String inputString = scannerSearch.next().toUpperCase();  // Benutzereingabe als String lesen
            Matcher opMatcher = searchPattern.matcher(inputString);
            //ich hatte davor die Daten als Int gesucht hier muss es aber String sein!!

            if (!opMatcher.matches()) {
                System.out.println("Falsche Eingabe");
                return;
            }
            for (int i = 0; i < json.length(); i++) {
                JSONObject empObject = json.getJSONObject(i);
                if (empObject.has("Materialkurztext") && empObject.getString("Materialkurztext").equals(inputString)) {
                    result = empObject;
                    break;
                }
            }
            if (result != null) {
                System.out.println("Ware gefunden");
                System.out.println("Materialnummer: " + result.getLong("Material"));
                System.out.println("Tarifnummer: " + result.getLong("EZT"));
                System.out.println("Materialkurztext: " + result.getString("Materialkurztext"));
                System.out.println("Text: " + result.getString("Übersetzung"));


            } else {
                System.out.println("Ware nicht gefunden");
            }
            scannerSearch.close();
            scanner.close();
            //beide Scanner hier schließen damit die JSON Datei richtig durchlesen wird

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}*/






