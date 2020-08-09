package ui.panels;

import model.Patient;
import model.Patients;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class RegisterPatientPanel extends JPanel {

    private static final String SOUND_FILE = "./data/sound/popupSound.wav";
    private static final String ERROR_IMAGE = "./data/image/iconError.png";

    private GridBagConstraints constraints;
    private JTextField phnTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private Patients patients;

    // MODIFIES:
    // EFFECTS:
    public RegisterPatientPanel(Patients patients) {
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
        registerButtonSetUp();
    }

    // MODIFIES:
    // EFFECTS:
    private void borderSetUp() {
        Border border = BorderFactory.createEtchedBorder();
        border = BorderFactory.createTitledBorder(border, "REGISTER PATIENT");
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
    //            https://stackoverflow.com/a/35393356
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

        phnTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (phnTextField.getText().length() >= 5) {
                    e.consume();
                }
            }
        });
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html
    //            https://stackoverflow.com/a/15526361
    private void registerButtonSetUp() {
        JButton registerButton = new JButton("Register");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 5, 0);
        add(registerButton, constraints);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personalHealthNumer = phnTextField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                validate(personalHealthNumer);
                Patient patient = new Patient(personalHealthNumer, firstName, lastName);
                patients.addPatient(patient);
                phnTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
            }
        });
    }

    private void validate(String personalHealthNumber) {
        ImageIcon icon = new ImageIcon(ERROR_IMAGE);
        if (phnTextField.getText().length() < 5) {
            playSound();
            JOptionPane.showConfirmDialog(null, "Please enter a valid personal health number.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        }
        if (patients.containsPatient(personalHealthNumber)) {
            playSound();
            JOptionPane.showConfirmDialog(null, "Patient is already registered in the system.",
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        }
    }


    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/a/15526480
    private void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(SOUND_FILE).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Sound file not found.");
        }
    }
}

