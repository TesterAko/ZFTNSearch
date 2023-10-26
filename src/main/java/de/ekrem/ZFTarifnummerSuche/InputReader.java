package de.ekrem.ZFTarifnummerSuche;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class InputReader {
    private static InputReader INSTANCE;
    private Scanner scanner;

    public static InputReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InputReader();
        }

        return INSTANCE;
    }

    private InputReader() {

        InputTextArea.getInstance().getTextArea().getText().split("\\n");
        InputStream inputStream = new ByteArrayInputStream(InputTextArea.getInstance().getTextArea().getText().getBytes());

       new InputStreamWorker(InputTextArea.getInstance().getTextArea(), inputStream);

    }

    // GET: /api/ekrem/materials  --> returns all
    // GET: /api/ekrem/materials/{id}  --> returns by id

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


    private class InputStreamWorker extends SwingWorker<Void, String> {
        private JTextArea textArea;
        private InputStreamWorker(JTextArea textArea, InputStream inStream) {
            this.textArea = textArea;
            scanner = new Scanner(inStream);
        }

        @Override
        protected Void doInBackground() throws Exception {
            while (scanner.hasNextLine()) {
                publish(scanner.nextLine());
            }
            return null;
        }

        @Override
        protected void process(List<String> chunks) {
            for (String chunk : chunks) {
                textArea.append(chunk + "\n");
            }
        }
    }
}

