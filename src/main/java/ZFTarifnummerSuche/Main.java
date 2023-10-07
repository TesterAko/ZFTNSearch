package ZFTarifnummerSuche;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static ZFTarifnummerSuche.Intro.intro;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        intro();

        Scanner scannerTNSearch = new Scanner(System.in);//Anfangen des Scanner-Objekts
        boolean isRunning = true;//startet das Programmm solange true ist
        while (isRunning) {
            try {
                if (scannerTNSearch.hasNextInt()) {
                    String inputLine = scannerTNSearch.nextLine();
                    Integer option = Integer.valueOf(inputLine);
                    switch (option) {
                        case 1:
                            TNSucheMaterialNr.tnSucheMaterialNr();
                            System.out.println("Möchten Sie weitersuchen? Ja [1] | Nein [2]");
                            Integer answer = Integer.valueOf(scannerTNSearch.nextLine());
                            if (answer == 1) {
                                continue;
                            } else {
                                isRunning = false;
                                intro();
                            }
                            //aktuell beendet das Programm nach der Suche was erfolreich ist
                        case 2:
                            TNSucheKurzText.tnSucheKurzText();
                            break;
                        case 3:
                            System.out.println("Das Programm wird beendet.");
                            isRunning = false;
                            break;
                        default:
                            System.out.println("Bitte geben Sie eine gültige Option ein.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Falsche Eingabe.");

            }//else bedingung gelöscht jetzt geht es!!!
        }
        scannerTNSearch.close();
    }
}


