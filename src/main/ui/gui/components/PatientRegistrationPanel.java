package ui.gui.components;

import model.Patient;
import model.Patients;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Represents a patient registration panel for BiosyncGUI
public class PatientRegistrationPanel extends JPanel {
    private static final String ERROR_IMAGE = "./data/image/iconError.png";

    private GridBagConstraints constraints;
    private JTextField phnTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private Patients patients;

    // MODIFIES: this
    // EFFECTS: constructs a patient registration panel with text fields and a button
    public PatientRegistrationPanel(Patients patients) {
        this.patients = patients;

        Dimension panelDimension = this.getPreferredSize();
        panelDimension.height = 150;
        this.setPreferredSize(panelDimension);
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 0, 0);

        Border border = BorderFactory.createEtchedBorder();
        border = BorderFactory.createTitledBorder(border, "  Patient Registration   ");
        this.setBorder(border);

        labelSetUp();
        textFieldSetUp();
        registerButtonSetUp();
    }

    // MODIFIES: this
    // EFFECTS: adds labels for text fields in this
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void labelSetUp() {
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel phnLabel = new JLabel("Personal Health Number:   ");
        this.add(phnLabel, constraints);

        constraints.gridy = 2;
        JLabel firstNameLabel = new JLabel("First name: ");
        this.add(firstNameLabel, constraints);

        constraints.gridy = 3;
        JLabel lastNameLabel = new JLabel("Last name: ");
        this.add(lastNameLabel, constraints);
    }

    // MODIFIES: this
    // EFFECTS: adds personal health number, first name, and last name text fields to this;
    //          personalHealthNumberTextField consumes characters that exceed the limit (5 characters)
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    //            https://stackoverflow.com/a/35393356 (limit number of characters inputted in JTextField)
    private void textFieldSetUp() {
        constraints.gridx = 1;
        constraints.gridy = 1;
        phnTextField = new JTextField(15);
        this.add(phnTextField, constraints);

        constraints.gridy = 2;
        firstNameTextField = new JTextField(15);
        this.add(firstNameTextField, constraints);

        constraints.gridy = 3;
        lastNameTextField = new JTextField(15);
        this.add(lastNameTextField, constraints);

        phnTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (phnTextField.getText().length() >= 5) {
                    e.consume();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: constructs a register button; when clicked, validates inputs and resets text fields
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void registerButtonSetUp() {
        JButton registerButton = new JButton("Register");
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(registerButton, constraints);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personalHealthNumber = phnTextField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();

                if (checkValidInput(personalHealthNumber, firstName, lastName)) {
                    patients.addPatient(new Patient(personalHealthNumber, firstName, lastName));
                }

                resetTextFields();
            }
        });
    }

    // EFFECTS: returns true if given inputs are valid, otherwise returns false
    // NOTE: Valid inputs are
    //         - phnTextField : numeric, has length of 5 characters, and has not yet been registered
    //         - first and lastNameTextField: non-empty string
    private boolean checkValidInput(String personalHealthNumber, String firstName, String lastName) {
        SoundPlayer soundPlayer = new SoundPlayer();
        ImageIcon iconError = new ImageIcon(ERROR_IMAGE);
        soundPlayer.playPopUpSound();

        if (phnTextField.getText().length() < 5 || !(personalHealthNumber.matches("^[0-9]*$"))) {
            JOptionPane.showConfirmDialog(null, "Please enter a valid personal health number.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, iconError);
            return false;
        } else if (patients.containsPatient(personalHealthNumber)) {
            JOptionPane.showConfirmDialog(null, "Patient is already registered in the system.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, iconError);
            return false;
        } else if (firstName.equals("") || lastName.equals("")) {
            JOptionPane.showConfirmDialog(null, "Please fill out the required fields.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, iconError);
            return false;
        }

        return true;
    }

    // MODIFIES: this
    // EFFECTS: clears all text fields
    private void resetTextFields() {
        phnTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
    }
}

