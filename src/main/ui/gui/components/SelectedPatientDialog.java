package ui.gui.components;

import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents panel that displays a selected patient's medical information
public class SelectedPatientDialog extends JDialog {
    private static final String FAVICON = "./data/image/favicon.png";
    private static final String ROBOT_IMAGE = "./data/image/baymax.gif";
    private static final Color BACKGROUND_COLOUR = new Color(186, 221, 255);

    private GridBagConstraints constraints;
    private Patient patient;

    // MODIFIES: this
    // EFFECTS: constructs a selected patient dialog window
    public SelectedPatientDialog(Patient patient) {
        this.patient = patient;

        this.setTitle("BIOSYNC - " + patient.getFirstName().toUpperCase()
                + " " + patient.getLastName().toUpperCase());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(BACKGROUND_COLOUR);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.setModal(true);

        ImageIcon favicon = new ImageIcon(FAVICON);
        this.setIconImage(favicon.getImage());

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;
        textAreaContentsSetUp();

        constraints.insets = new Insets(0, 0, 0, 20);
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 6;
        JLabel robot = new JLabel(new ImageIcon(ROBOT_IMAGE));
        this.add(robot, constraints);

        pack();
        this.setLocationRelativeTo(null);
    }

    // EFFECTS: constructs a textArea and returns it
    private JTextArea textAreaSetUp() {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(BACKGROUND_COLOUR);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        return textArea;
    }

    // MODIFIES: this
    // EFFECTS: sets up text areas and their contents
    private void textAreaContentsSetUp() {
        JTextArea identification = textAreaSetUp();
        formatHeading(identification, patient.getPersonalHealthNumber() + "   ");
        formatHeading(identification, patient.getFirstName().toUpperCase() + " "
                + patient.getLastName().toUpperCase());

        JTextArea diagnosesLabel = textAreaSetUp();
        JTextArea diagnoses = textAreaSetUp();
        formatSubheading(diagnosesLabel, "DIAGNOSES", 2);
        formatList(diagnoses, patient.getDiagnoses(), 3);

        JTextArea medicationsLabel = textAreaSetUp();
        JTextArea medications = textAreaSetUp();
        formatSubheading(medicationsLabel, "MEDICATIONS", 4);
        formatList(medications, patient.getMedications(), 5);
    }

    // MODIFIES: this
    // EFFECTS: formats textArea as a heading and adds it to this
    private void formatHeading(JTextArea textArea, String text) {
        Font headingFont = new Font("Dialog", Font.BOLD, 16);
        textArea.setFont(headingFont);
        textArea.append(text);

        constraints.insets = new Insets(20, 20, 0, 20);
        add(textArea, constraints);
    }

    // MODIFIES: this
    // EFFECTS: formats textArea as a subheading and adds it to this
    private void formatSubheading(JTextArea textArea, String text, int y) {
        Font headingFont = new Font("Dialog", Font.BOLD + Font.ITALIC, 14);
        textArea.setFont(headingFont);
        textArea.append(text);

        constraints.gridy = y;
        add(textArea, constraints);

        constraints.insets = new Insets(0, 20, 0, 20);
    }

    // MODIFIES: this
    // EFFECTS: formats elements of an ArrayList into bullet points and adds it to this
    private void formatList(JTextArea textArea, ArrayList<String> list, int y) {
        Font body = new Font("Dialog", Font.PLAIN, 14);
        textArea.setFont(body);
        for (String s : list) {
            textArea.append("  -   " + s.charAt(0) + s.substring(1).toLowerCase() + "\n");
        }

        constraints.gridy = y;
        add(textArea, constraints);
    }

}

