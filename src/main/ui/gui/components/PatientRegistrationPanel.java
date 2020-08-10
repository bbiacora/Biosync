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

public class PatientRegistrationPanel extends JPanel {
    private static final String ERROR_IMAGE = "./data/image/iconError.png";

    private GridBagConstraints constraints;
    private JTextField phnTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private Patients patients;

    // MODIFIES: this
    // EFFECTS:
    public PatientRegistrationPanel(Patients patients) {
        Dimension panelDimension = this.getPreferredSize();
        panelDimension.height = 150;
        this.setPreferredSize(panelDimension);

        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 0, 0);

        this.patients = patients;

        borderSetUp();
        labelSetUp();
        textFieldSetUp();
        registerButtonSetUp();
    }

    // MODIFIES: this
    // EFFECTS: adds a border around this panel
    private void borderSetUp() {
        Border border = BorderFactory.createEtchedBorder();
        border = BorderFactory.createTitledBorder(border, "  Patient Registration   ");
        this.setBorder(border);
    }

    // MODIFIES: this
    // EFFECTS: adds labels for text fields in this panel
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
    // EFFECTS: adds text fields to this panel
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    //            https://stackoverflow.com/a/35393356
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
    // EFFECTS: adds a button to this panel
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    //            https://stackoverflow.com/a/15526361
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
                    Patient patient = new Patient(personalHealthNumber, firstName, lastName);
                    patients.addPatient(patient);
                }

                resetTextFields();
            }
        });
    }


    // EFFECTS: returns true if given inputs are valid, otherwise returns false
    private boolean checkValidInput(String personalHealthNumber, String firstName, String lastName) {
        SoundPlayer soundPlayer = new SoundPlayer();
        ImageIcon iconError = new ImageIcon(ERROR_IMAGE);

        if (phnTextField.getText().length() < 5 || !(personalHealthNumber.matches("^[0-9]*$"))) {
            soundPlayer.playPopUpSound();
            JOptionPane.showConfirmDialog(null, "Please enter a valid personal health number.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, iconError);
            return false;
        } else if (patients.containsPatient(personalHealthNumber)) {
            soundPlayer.playPopUpSound();
            JOptionPane.showConfirmDialog(null, "Patient is already registered in the system.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, iconError);
            return false;
        } else if (firstName.equals("") || lastName.equals("")) {
            soundPlayer.playPopUpSound();
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

