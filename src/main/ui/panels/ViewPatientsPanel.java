package ui.panels;

import model.Patient;
import model.Patients;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ViewPatientsPanel extends JPanel {
    private static final String SOUND_FILE = "./data/sound/popupSound.wav";
    private static final String QUESTION_IMAGE = "./data/image/iconQuestion.png";
    private static final String[] HEADERS = {"Personal Health Number", "Patient's Name"};
    private static final int TABLE_WIDTH = 550;
    private static final int TABLE_HEIGHT = 100;
    private GridBagConstraints constraints;
    private JTable table;
    private DefaultTableModel model;
    private Patients patients;
    private ArrayList<Patient> patientsList;

    // MODIFIES:
    // EFFECTS:
    public ViewPatientsPanel(Patients patients) {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 15, 0);

        this.patients = patients;

        tableSetUp(patients);
        removeButtonSetUp();
        updateButtonSetUp();
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    //            https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
    private void tableSetUp(Patients patients) {
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(new Color(186, 221, 255));
        table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(20, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(HEADERS);
        showPatientsInTable(table, patients);
        JScrollPane scroll = new JScrollPane(table);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(scroll, constraints);
    }

    // MODIFIES:
    // EFFECTS:
    private void removeButtonSetUp() {
        JButton removeButton = new JButton("Remove");
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        add(removeButton, constraints);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    int row = table.getSelectedRow();
                    String personalHealthNumber = table.getModel().getValueAt(row, 0).toString();
                    playSound();
                    confirmRemoval(personalHealthNumber);
                } catch (Exception e) {
                    System.err.println("Selection was not made.");
                }
            }
        });
    }

    // MODIFIES:
    // EFFECTS:
    private void updateButtonSetUp() {
        JButton updateButton = new JButton(" Update ");
        constraints.anchor = GridBagConstraints.LINE_START;
        add(updateButton, constraints);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPatientsInTable(table, patients);
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: populates table with patient information of patients in an ArrayList
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    //            https://stackoverflow.com/questions/3879610/how-to-clear-contents-of-a-jtable
    private void showPatientsInTable(JTable table, Patients patients) {
        patientsList = patients.getPatientsList();
        model.setRowCount(0);
        for (Patient p : patientsList) {
            model.addRow(new Object[]{p.getPersonalHealthNumber(), p.getLastName() + ", " + p.getFirstName()});
        }
        table.setModel(model);
    }

    // Reference: https://www.tutorialspoint.com/how-to-create-a-confirmation-dialog-box-in-java
    //            https://stackoverflow.com/a/38981623
    private void confirmRemoval(String personalHealthNumber) {
        ImageIcon icon = new ImageIcon(QUESTION_IMAGE);
        int response = JOptionPane.showConfirmDialog(null,
                "Are you sure? This process cannot be undone.",
                "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

        if (response == 0) {
            patients.removePatient(personalHealthNumber);
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
