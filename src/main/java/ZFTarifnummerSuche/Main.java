package ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;

import static ZFTarifnummerSuche.TerminalDecorator.intro;

public class Main {
    private static final InputReader INPUT_READER = InputReader.getInstance();
    private static final JsonFileReader FILE_READER = JsonFileReader.getInstance();

    public static void main(String[] args) {
        TNSearchService searchService = new TNSearchService();
        JSONArray json = FILE_READER.readrJsonFile();

        doSearch(searchService, json);
    }

    private static void doSearch(TNSearchService searchService, JSONArray json) {
        intro();
        int option = INPUT_READER.readOptions();
        switch (option) {
            case 1:
                int inputNumber = INPUT_READER.readMaterialNumber();
                JSONObject result = searchService.tnSucheMaterialNr(inputNumber, json);
                if (result == null) {
                    System.out.println("Nothing was found");
                    if (INPUT_READER.askToContinue()) {
                        doSearch(searchService, json);
                    }
                } else {
                    TerminalDecorator.printResult(result);
                }

                if (INPUT_READER.askToContinue()) {
                    doSearch(searchService, json);
                }
                break;
            case 2:
                // TNSucheKurzText.tnSucheKurzText();
                System.out.println("Skip fo now");
                if (INPUT_READER.askToContinue()) {
                    doSearch(searchService, json);
                }
                break;
            case 3:
                System.out.println("Das Programm wird beendet.");
                INPUT_READER.close();
                FILE_READER.close();
                System.exit(1);
                break;
            default:
                System.out.println("Bitte geben Sie eine gültige Option ein.");
        }
    }
}


