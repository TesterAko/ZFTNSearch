import de.ekrem.ZFTarifnummerSuche.JsonFileReader;
import de.ekrem.ZFTarifnummerSuche.TNSearchService;
import de.ekrem.ZFTarifnummerSuche.TerminalDecorator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

import static de.ekrem.ZFTarifnummerSuche.Main.*;
import static de.ekrem.ZFTarifnummerSuche.TerminalDecorator.menu;
import static java.lang.System.in;

public class MainGUI {

    //static final InputReader INPUT_READER = InputReader.getInstance();
    static final JsonFileReader FILE_READER = JsonFileReader.getInstance();

    static void executeGUI() {
        JFrame frame = new JFrame("ZF Tarifnummersuche");
        frame.setSize(300, 500);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);

        JLabel introLabel = TerminalDecorator.intro();
        frame.getContentPane().add(introLabel, BorderLayout.NORTH);

        JTextArea commandLineArea = new JTextArea(10, 100);
        commandLineArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(commandLineArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        /*InputStream inputStream = new InputStream(new CustomInputStream(commandLineArea));
        System.setIn(inputStream);
        */

        PrintStream printStream = new PrintStream(new CustomOutputStream(commandLineArea));
        System.setOut(printStream);

        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> runCommandLine(commandLineArea));

    }

    private static void runCommandLine(JTextArea commandLineArea) {
        TNSearchService searchService = new TNSearchService();
        JSONArray json = FILE_READER.readrJsonFile();
        /*int option = INPUT_READER.readOptions();
        int inputNumber = INPUT_READER.readMaterialNumber();*/

        PrintStream printStream = new PrintStream(new CustomOutputStream(commandLineArea));
        System.setOut(printStream);
       /* InputStream inputStream = new InputStream(new CustomInputStream(commandLineArea));
        System.setIn(inputStream);*/

        new Thread(() -> doSearch(searchService, json)).start();

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