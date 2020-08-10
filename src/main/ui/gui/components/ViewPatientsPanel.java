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

// Represents a panel that displays registered patients onto BiosyncGUI
public class ViewPatientsPanel extends JPanel {
    private static final String QUESTION_IMAGE = "./data/image/iconQuestion.png";
    private static final String[] HEADERS = {"Personal Health Number", "Patient's Name"};
    private static final Color HEADER_COLOUR = new Color(186, 221, 255);
    private static final int TABLE_WIDTH = 550;
    private static final int TABLE_HEIGHT = 100;

    private GridBagConstraints constraints;
    private JTable table;
    private DefaultTableModel model;
    private Patients patients;

    // MODIFIES: this
    // EFFECTS: constructs a panel that displays registered patients in a table with buttons,
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

    // MODIFIES: this
    // EFFECTS: constructs a table; when a table row is double clicked,
    //          displays selectedPatientPanel for the patient in the selected row
    // Reference: https://stackoverflow.com/a/4051681 (mouse event: double left click)
    //            https://stackoverflow.com/a/6750561 (modal dialog pane)
    private void tableSetUp() {
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(HEADER_COLOUR);
        table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(20, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    String personalHealthNumber = getPersonalHealthNumberFromRow();
                    Patient patient = patients.getPatient(personalHealthNumber);
                    SelectedPatientDialog selectedPatient = new SelectedPatientDialog(patient);
                    selectedPatient.setVisible(true);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: populates table with patient data, organizes contents of table
    // Reference: https://stackoverflow.com/a/3134006 (non-editable cells)
    private void tableContentsSetUp() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(HEADERS);

        showPatientsInTable();

        constraints.gridx = 0;
        constraints.gridy = 0;
        JScrollPane scroll = new JScrollPane(table);
        this.add(scroll, constraints);
    }

    // MODIFIES: this
    // EFFECTS: constructs a remove button;
    //          when clicked and a row is selected, retrieves personal health number from row,
    //          displays a dialog box that asks user for patient removal confirmation
    //          - OR -
    //          when clicked and a row is NOT selected, does nothing
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
                    //do nothing
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: constructs an update button;
    //          when clicked, reloads table
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
    // EFFECTS: populates table with personal health number and name of a patients in patients
    // Reference: https://stackoverflow.com/a/35691199 (populate JTable with data from elements in ArrayList)
    public void showPatientsInTable() {
        ArrayList<Patient> patientsList = patients.getPatientsList();
        model.setRowCount(0);
        for (Patient p : patientsList) {
            model.addRow(new Object[]{p.getPersonalHealthNumber(), p.getFirstName() + " " + p.getLastName()});
        }
        table.setModel(model);
    }

    // MODIFIES: this
    // EFFECTS: plays a pop up sound,
    //          constructs and displays a dialog box asking for patient removal confirmation;
    //          if user clicks 'OK' button of the dialog box, removes patient from patient
    // Reference: https://www.tutorialspoint.com/how-to-create-a-confirmation-dialog-box-in-java
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

    // EFFECTS: returns personalHealthNumber in selected row
    // Reference: https://stackoverflow.com/a/38981623 (retrieve value from selected row)
    private String getPersonalHealthNumberFromRow() {
        int row = table.getSelectedRow();
        return table.getModel().getValueAt(row, 0).toString();
    }

}
