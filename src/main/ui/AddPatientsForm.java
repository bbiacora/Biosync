package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientsForm extends JPanel {
    private GridBagConstraints gc;

    public AddPatientsForm() {
        Dimension size = getPreferredSize();
        size.height = 150;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("ADD A NEW PATIENT"));

        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        labelSetUp();
        fieldSetUp();
        buttonSetUp();
    }

    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void labelSetUp() {
        JLabel phnLabel = new JLabel("Personal Health Number: ");
        JLabel firstNameLabel = new JLabel("First name: ");
        JLabel lastNameLabel = new JLabel("Last name: ");

        //// First column ////
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(phnLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        add(firstNameLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        add(lastNameLabel, gc);
    }

    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    private void fieldSetUp() {
        JTextField phnField = new JTextField(15);
        JTextField firstNameField = new JTextField(15);
        JTextField lastNameField = new JTextField(15);

        //// Second column ////
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        add(phnField, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(firstNameField, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(lastNameField, gc);
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
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 4;
        add(addButton, gc);
    }
}

