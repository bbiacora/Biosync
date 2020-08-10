package ui.gui.components;

import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectedPatientDialog extends JDialog {
    private static final String FAVICON = "./data/image/favicon.png";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 443;
    private JPanel panel;
    //    private JTextArea textArea;
    private Patient patient;
    private GridBagConstraints constraints;

    // MODIFIES:
    // EFFECTS:
    public SelectedPatientDialog(Patient patient) {
        this.patient = patient;

        this.setTitle("BIOSYNC - " + patient.getFirstName().toUpperCase() + " " + patient.getLastName().toUpperCase());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setModal(true);

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;

        ImageIcon favicon = new ImageIcon(FAVICON);
        this.setIconImage(favicon.getImage());
        textAreaContentsSetUp();
        pack();
        this.setLocationRelativeTo(null);
    }

    private void textAreaSetUp(JTextArea textArea) {
        textArea.setBackground(new Color(238, 238, 238));
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
    }

    private void textAreaContentsSetUp() {
        JTextArea general = new JTextArea();
        JTextArea diagnosesLabel = new JTextArea();
        JTextArea medicationsLabel = new JTextArea();
        JTextArea diagnoses = new JTextArea();
        JTextArea medications = new JTextArea();
        textAreaSetUp(general);
        textAreaSetUp(diagnosesLabel);
        textAreaSetUp(medicationsLabel);
        textAreaSetUp(diagnoses);
        textAreaSetUp(medications);


        formatHeading(general, patient.getPersonalHealthNumber() + "   ", 0, 0);
        formatHeading(general, patient.getFirstName().toUpperCase() + " " + patient.getLastName().toUpperCase() + "\n", 0, 0);
        formatHeading(diagnosesLabel, "DIAGNOSES", 0, 2);
        formatList(diagnoses, patient.getDiagnoses(), 0, 3);
        formatHeading(medicationsLabel, "MEDICATIONS", 0, 4);
        formatList(medications, patient.getMedications(), 0, 5);
    }

    private void formatHeading(JTextArea textArea, String text, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        textAreaSetUp(textArea);
        Font headingFont = new Font("Dialog", Font.BOLD, 14);
        textArea.setFont(headingFont);
        textArea.append(text);
        add(textArea, constraints);
    }

    // EFFECTS: formats elements of an ArrayList into bullet points and adds it to textArea
    private void formatList(JTextArea textArea, ArrayList<String> list, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        Font body = new Font("Dialog", Font.PLAIN, 14);
        textArea.setFont(body);
        for (String s : list) {
            textArea.append("  -   " + s.substring(0, 1) + s.substring(1).toLowerCase() + "\n");
        }
        textArea.append("\n");
        add(textArea, constraints);
    }

}

