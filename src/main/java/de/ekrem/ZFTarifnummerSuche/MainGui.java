package de.ekrem.ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;

import static de.ekrem.ZFTarifnummerSuche.TerminalDecorator.menu;

public class MainGui {

  static final InputReader INPUT_READER = InputReader.getInstance();
  static final JsonFileReader FILE_READER = JsonFileReader.getInstance();

  static void executeGUI() {
    SwingUtilities.invokeLater(MainGui::runCommandLine);
  }

  private static void runCommandLine() {
    TNSearchService searchService = new TNSearchService();
    JSONArray json = FILE_READER.readrJsonFile();

    PrintStream printStream = new PrintStream(new CustomOutputStream(InputTextArea.getInstance()
        .getTextArea()));
    System.setOut(printStream);

   doSearch(searchService, json);

  }

    /*private static class CustomInputStream extends InputStream {
        private JTextArea textArea = null;

        public CustomInputStream(JTextArea commandLineArea) {
            this.textArea = commandLineArea;
        }

        @Override
        public int read() throws IOException {
            return 0;
        }
    }*/

  public static class CustomOutputStream extends OutputStream {

    private final JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
      this.textArea = textArea;
    }

    @Override
    public void write(int b) {
      SwingUtilities.invokeLater(() -> {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
      });
    }
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