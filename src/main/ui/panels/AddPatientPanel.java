package ui.panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientPanel extends JPanel {
    private GridBagConstraints constraints;

    public AddPatientPanel() {
        Dimension size = getPreferredSize();
        size.height = 150;
        setPreferredSize(size);

        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        borderSetUp();
        labelSetUp();
        textFieldSetUp();
        buttonSetUp();
    }


    private void borderSetUp() {
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        border = BorderFactory.createTitledBorder(border, "ADD A NEW PATIENT");
        setBorder(border);
    }

    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void labelSetUp() {
        JLabel phnLabel = new JLabel("Personal Health Number: ");
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

    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void textFieldSetUp() {
        JTextField phnField = new JTextField(15);
        JTextField firstNameField = new JTextField(15);
        JTextField lastNameField = new JTextField(15);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(phnField, constraints);
        constraints.gridy = 2;
        add(firstNameField, constraints);
        constraints.gridy = 3;
        add(lastNameField, constraints);
    }

    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void buttonSetUp() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        //// Final row ////
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridy = 4;
        add(addButton, constraints);
    }
}

