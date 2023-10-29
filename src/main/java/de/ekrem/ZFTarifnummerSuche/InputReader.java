package de.ekrem.ZFTarifnummerSuche;

import java.util.Scanner;

public class InputReader {
    private static InputReader INSTANCE;
    private final Scanner scanner;

    public static InputReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InputReader();
        }

        return INSTANCE;
    }

    private InputReader() {
        this.scanner = new Scanner(System.in);

    }

    public int readOptions() {
        String inputLine = scanner.nextLine();
        try {
            int option = Integer.parseInt(inputLine);
            if (option > 3 || option < 1) {
                System.out.println("Bitte benutze 1, 2 oder 3.");
                return readOptions();
            }
            return option;
        } catch (NumberFormatException e) {
            System.out.println("Falsche Eingabe.");
            return readOptions();
        }
    }

    public int readMaterialNumber() {
        System.out.println("Suche nach Materialnummern");
        String inputLine = scanner.nextLine();
        try {
            return (int) Long.parseLong(inputLine);
        } catch (NumberFormatException e) {
            System.out.println("Falsche Eingabe.");
            return readMaterialNumber();
        }
    }

    public String readText() {

        System.out.println("Suche nach Kurztext");
        String inputLine = scanner.nextLine();
        //ist keine Int brauche kein Numberformatexception
        String result = inputLine.toUpperCase();
        return result;
    }

    public void close() {
        scanner.close();
    }

    public boolean askToContinue() {
        return true;
    }
}