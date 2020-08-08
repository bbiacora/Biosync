package ui.panels;

import model.Patient;
import model.Patients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewPatientsPanel extends JPanel {
    private static final String[] HEADERS = {"Personal Health Number", "Patient's Name"};
    private static final int TABLE_WIDTH = 550;
    private static final int TABLE_HEIGHT = 100;
    private GridBagConstraints constraints;
    private JTable table;
    private DefaultTableModel model;
    private Patients patients;
    private ArrayList<Patient> patientsList;

    // EFFECTS:
    public ViewPatientsPanel(Patients patients) {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 15, 0);

        this.patients = patients;
        patientsList = patients.getPatientsList();

        tableSetUp(patientsList);
        buttonsSetUp();
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    //            https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
    private void tableSetUp(ArrayList<Patient> patientsList) {
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        table.getTableHeader().setReorderingAllowed(false);
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
        showPatientsInTable(table, patientsList);
        JScrollPane scroll = new JScrollPane(table);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(scroll, constraints);
    }

    // MODIFIES:
    // EFFECTS:
    private void buttonsSetUp() {
        JButton removeButton = new JButton("Remove");
        JButton updateButton = new JButton(" Update ");
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        add(removeButton, constraints);
        constraints.anchor = GridBagConstraints.LINE_START;
        add(updateButton, constraints);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String value = table.getModel().getValueAt(row, 0).toString();
                confirmRemoval(value);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: populates table with patient information of patients in an ArrayList
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    public void showPatientsInTable(JTable table, ArrayList<Patient> patientsList) {
        for (Patient p : patientsList) {
            model.addRow(new Object[]{p.getPersonalHealthNumber(), p.getLastName() + ", " + p.getFirstName()});
        }
        table.setModel(model);
    }

    // Reference: https://www.tutorialspoint.com/how-to-create-a-confirmation-dialog-box-in-java
    //            https://stackoverflow.com/a/38981623
    public void confirmRemoval(String value) {
        int response = JOptionPane.showConfirmDialog(null,
                "Are you sure? This process cannot be undone.", " Confirm", JOptionPane.OK_CANCEL_OPTION);
        if (response == 0) {
//            patients.removePatient(value);
        }
    }
}
