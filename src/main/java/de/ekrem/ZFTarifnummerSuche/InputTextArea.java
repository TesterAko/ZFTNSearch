package de.ekrem.ZFTarifnummerSuche;

import static de.ekrem.ZFTarifnummerSuche.TerminalDecorator.introHtml;

import de.ekrem.ZFTarifnummerSuche.MainGui.CustomOutputStream;
import java.awt.BorderLayout;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class InputTextArea {

  private static InputTextArea INSTANCE;

  private JTextArea commandLineArea;
  public static InputTextArea getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InputTextArea();
    }

    return INSTANCE;
  }

  private InputTextArea() {
    JFrame frame = new JFrame("ZF Tarifnummersuche");
    frame.setSize(300, 500);
    frame.setLocation(100, 150);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JFrame.setDefaultLookAndFeelDecorated(true);


    frame.getContentPane().add(introHtml(), BorderLayout.NORTH);


    commandLineArea = new JTextArea(10, 100);
    commandLineArea.setEditable(true);
    JScrollPane scrollPane = new JScrollPane(commandLineArea);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

    frame.setVisible(true);
  }

  public JTextArea getTextArea(){
    return this.commandLineArea;
  }
}
