package de.ekrem.ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;


import static de.ekrem.ZFTarifnummerSuche.MainGui.executeGUI;
import static de.ekrem.ZFTarifnummerSuche.TerminalDecorator.intro;
import static de.ekrem.ZFTarifnummerSuche.TerminalDecorator.menu;

public class Main {

    private static final InputReader INPUT_READER = InputReader.getInstance();
    private static final JsonFileReader FILE_READER = JsonFileReader.getInstance();

    public static void main(String[] args) {
        //intro();
       // TNSearchService searchService = new TNSearchService();
       // JSONArray json = FILE_READER.readrJsonFile();

        executeGUI();
       // doSearch(searchService, json);
    }


    private static void doSearch(TNSearchService searchService, JSONArray json) {
        int option = INPUT_READER.readOptions();
        switch (option) {
            case 1 -> {
                int inputNumber = INPUT_READER.readMaterialNumber();
                JSONObject result = searchService.tnSucheMaterialNr(inputNumber, json);
                if (result == null) {
                    System.out.println("Nichts gefunden!");
                    System.out.println("Bitte versuchen Sie es erneut");
                    menu();
                    if (INPUT_READER.askToContinue()) {
                        doSearch(searchService, json);
                    }
                } else {
                    TerminalDecorator.printResult(result);
                    menu();
                }
                if (INPUT_READER.askToContinue()) {
                    doSearch(searchService, json);
                }
            }
            case 2 -> {
                String kurzText = INPUT_READER.readText();
                JSONObject resultText = searchService.tnSucheKurzText(kurzText, json);
                if (resultText == null) {
                    System.out.println("Nichts gefunden!");
                    System.out.println("Bitte versuchen Sie es erneut");
                    menu();
                    if (INPUT_READER.askToContinue()) {
                        doSearch(searchService, json);
                    }
                } else {
                    TerminalDecorator.printResult(resultText);
                    menu();
                }
                if (INPUT_READER.askToContinue()) {
                    doSearch(searchService, json);
                }
            }
            case 3 -> {
                System.out.println("Das Programm wird beendet.");
                INPUT_READER.close();
                FILE_READER.close();
                System.exit(1);
            }
            default -> System.out.println("Bitte geben Sie eine gültige Option ein.");
        }
    }
}


