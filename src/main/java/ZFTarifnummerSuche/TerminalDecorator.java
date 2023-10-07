package ZFTarifnummerSuche;

import org.json.JSONObject;

public class TerminalDecorator {
    public static void intro() {
        System.out.println("Herzlich Willkommen");
        System.out.println("===========================================");
        System.out.println("Hauptmenü");
        System.out.println("Für Suche nach Materialnummer drücken Sie 1");
        System.out.println("Für Suche nach Kurztext drücken Sie 2");
        System.out.println("Für Beenden drücken Sie 3");
        System.out.println("===========================================");
        System.out.println("Bitte geben Sie eine Option ein:");
        System.out.println("===========================================");
    }

    public static void menu() {
        System.out.println("Für Suche nach Materialnummer drücken Sie 1");
        System.out.println("Für Suche nach Kurztext drücken Sie 2");
        System.out.println("Für Beenden drücken Sie 3");

    }

    public static void printResult(JSONObject result) {
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
        System.out.println("\n");
    }
}
