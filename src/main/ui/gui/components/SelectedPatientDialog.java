package ui.gui.components;

import model.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class SelectedPatientDialog extends JDialog {
    private static final String FAVICON = "./data/image/favicon.png";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 443;

    private JTextArea textArea;
    private Patient patient;

    // MODIFIES:
    // EFFECTS:
    public SelectedPatientDialog(Patient patient) {
        this.patient = patient;

        this.setTitle("BIOSYNC - " + patient.getFirstName().toUpperCase() + " " + patient.getLastName().toUpperCase());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setModal(true);

        ImageIcon favicon = new ImageIcon(FAVICON);
        this.setIconImage(favicon.getImage());
        textAreaSetUp();
        textAreaContentsSetUp();
//        this.add(textArea);
        pack();
        this.setLocationRelativeTo(null);
    }

    private void textAreaSetUp() {
        textArea = new JTextArea();
        textArea.setBackground(new Color(238, 238, 238));
        textArea.setSize(new Dimension(WIDTH, HEIGHT));
        textArea.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
    }

    private void textAreaContentsSetUp() {
        Font heading = new Font("Dialog", Font.BOLD, 14);
        textArea.setFont(heading);
        textArea.append(patient.getPersonalHealthNumber() + "   ");
        textArea.append(patient.getFirstName() + " " + patient.getLastName() + "\n\n");

        formatList("DIAGNOSES:", patient.getDiagnoses());
        formatList("MEDICATIONS", patient.getMedications());
    }

    // EFFECTS: prints a bulleted list
    private void formatList(String title, ArrayList<String> list) {
        textArea.append(title + "\n");
        Font body = new Font("Dialog", Font.PLAIN, 14);
        textArea.setFont(body);
        for (int i = 0; i < list.size(); i++) {
            textArea.append("  ●  " + list.get(i) + "\n");
        }
        textArea.append("\n");
        this.add(textArea);
    }

}

