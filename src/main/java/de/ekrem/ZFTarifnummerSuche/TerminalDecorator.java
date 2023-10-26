package de.ekrem.ZFTarifnummerSuche;

import javax.swing.JLabel;
import org.json.JSONObject;

public class TerminalDecorator {

    public static JLabel introHtml() {

        String labelText = "<html><div style='text-align: center;'>" +
            "<h1>Herzlich Willkommen</h1>" +
            "<hr>" +
            "<p><h2>Hauptmenü</h2></p>" +
            "<p>Für Suche nach Materialnummer drücken Sie 1</p>" +
            "<p>Für Suche nach Kurztext drücken Sie 2</p>" +
            "<p>Für Beenden drücken Sie 3</p>" +
            "<hr>" +
            "<p>Bitte geben Sie eine Option ein:</p>" +
            "<hr>" +
            "</div></html>";

        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }

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

        System.out.println("Ware gefunden");
        System.out.println("Materialnummer: " + result.getLong("Material"));
        System.out.println("Tarifnummer: " + result.getLong("EZT"));
        System.out.println("Materialkurztext: " + result.getString("Materialkurztext"));
        System.out.println("Text: " + result.getString("Info"));
        System.out.println("\n");
    }
}
