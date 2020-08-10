package ui.gui.components;

import model.Patient;
import model.Patients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ViewPatientsPanel extends JPanel {
    private static final String QUESTION_IMAGE = "./data/image/iconQuestion.png";
    private static final String[] HEADERS = {"Personal Health Number", "Patient's Name"};
    private static final int TABLE_WIDTH = 550;
    private static final int TABLE_HEIGHT = 100;

    private GridBagConstraints constraints;
    private JTable table;
    private DefaultTableModel model;
    private Patients patients;

    // MODIFIES:
    // EFFECTS:
    public ViewPatientsPanel(Patients patients) {
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        this.patients = patients;

        tableSetUp();
        tableContentsSetUp();
        removeButtonSetUp();
        updateButtonSetUp();
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
    //            https://stackoverflow.com/questions/4051659/identifying-double-click-in-java
    private void tableSetUp() {
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(new Color(186, 221, 255));
        table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(20, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String personalHealthNumber = getPersonalHealthNumberFromRow();
                    Patient patient = patients.getPatient(personalHealthNumber);
                    SelectedPatientDialog selectedPatient = new SelectedPatientDialog(patient);
                    selectedPatient.setVisible(true);
                }
            }
        });
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    private void tableContentsSetUp() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(HEADERS);

        showPatientsInTable();

        JScrollPane scroll = new JScrollPane(table);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(scroll, constraints);
    }

    // MODIFIES:
    // EFFECTS:
    private void removeButtonSetUp() {
        constraints.insets.bottom = 20;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_END;

        JButton removeButton = new JButton("Remove");
        this.add(removeButton, constraints);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    String personalHealthNumber = getPersonalHealthNumberFromRow();
                    confirmRemoval(personalHealthNumber);
                    showPatientsInTable();
                } catch (Exception e) {
                    //keep going
                }
            }
        });
    }

    // MODIFIES:
    // EFFECTS:
    private void updateButtonSetUp() {
        constraints.anchor = GridBagConstraints.LINE_START;
        JButton updateButton = new JButton(" Update ");
        this.add(updateButton, constraints);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPatientsInTable();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: populates table with patient information of patients in an ArrayList
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    public void showPatientsInTable() {
        ArrayList<Patient> patientsList = patients.getPatientsList();
        model.setRowCount(0);
        for (Patient p : patientsList) {
            model.addRow(new Object[]{p.getPersonalHealthNumber(), p.getLastName() + ", " + p.getFirstName()});
        }
        table.setModel(model);
    }

    // Reference: https://www.tutorialspoint.com/how-to-create-a-confirmation-dialog-box-in-java
    //            https://stackoverflow.com/a/38981623
    private void confirmRemoval(String personalHealthNumber) {
        ImageIcon iconQuestion = new ImageIcon(QUESTION_IMAGE);
        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.playPopUpSound();

        int userResponse = JOptionPane.showConfirmDialog(null,
                "Are you sure? This process cannot be undone.",
                "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, iconQuestion);

        if (userResponse == 0) {
            patients.removePatient(personalHealthNumber);
        }
    }

    // MODIFIES:
    // EFFECTS:
    private String getPersonalHealthNumberFromRow() {
        int row = table.getSelectedRow();
        return table.getModel().getValueAt(row, 0).toString();
    }

}
