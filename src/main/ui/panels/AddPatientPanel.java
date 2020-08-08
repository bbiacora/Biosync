package ui.panels;

import model.Patient;
import model.Patients;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientPanel extends JPanel {
    private GridBagConstraints constraints;
    private JTextField phnTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private Patients patients;

    public AddPatientPanel(Patients patients) {
        Dimension size = getPreferredSize();
        size.height = 150;
        setPreferredSize(size);

        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 0, 0);

        this.patients = patients;

        borderSetUp();
        labelSetUp();
        textFieldSetUp();
        addButtonSetUp();
    }

    // MODIFIES:
    // EFFECTS:
    private void borderSetUp() {
        Border border = BorderFactory.createEtchedBorder();
        border = BorderFactory.createTitledBorder(border, "ADD PATIENT");
        setBorder(border);
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void labelSetUp() {
        JLabel phnLabel = new JLabel("Personal Health Number:   ");
        JLabel firstNameLabel = new JLabel("First name: ");
        JLabel lastNameLabel = new JLabel("Last name: ");

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(phnLabel, constraints);
        constraints.gridy = 2;
        add(firstNameLabel, constraints);
        constraints.gridy = 3;
        add(lastNameLabel, constraints);
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void textFieldSetUp() {
        phnTextField = new JTextField(15);
        firstNameTextField = new JTextField(15);
        lastNameTextField = new JTextField(15);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(phnTextField, constraints);
        constraints.gridy = 2;
        add(firstNameTextField, constraints);
        constraints.gridy = 3;
        add(lastNameTextField, constraints);
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    //            https://stackoverflow.com/a/15526361
    private void addButtonSetUp() {
        JButton addButton = new JButton("Add");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 5, 0);
        add(addButton, constraints);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personalHealthNumer = phnTextField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                Patient patient = new Patient(personalHealthNumer, firstName, lastName);
                patients.addPatient(patient);
                phnTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
            }
        });
    }
}

