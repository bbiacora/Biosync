package ui.panels;

import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewPatientsPanel extends JPanel {
    private static final String[] HEADERS = {"Personal Health Number", "Last name", "First Name"};
    private static final int TABLE_WIDTH = 550;
    private static final int TABLE_HEIGHT = 100;
    private GridBagConstraints constraints;
    private DefaultTableModel model;

    public ViewPatientsPanel(ArrayList<Patient> patientsList) {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 15, 0);

        tableSetUp(patientsList);
        buttonsSetUp();
    }

    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    //            https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
    private void tableSetUp(ArrayList<Patient> patientsList) {
        JTable table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(20, 0));
        table.getTableHeader().setReorderingAllowed(false);
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

    private void buttonsSetUp() {
        JButton removeButton = new JButton("Remove");
        JButton updateButton = new JButton(" Update ");

        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(removeButton, constraints);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(updateButton, constraints);
    }

    // MODIFIES:this
    // EFFECTS: populates table with patient information of patients in an ArrayList
    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    public void showPatientsInTable(JTable table, ArrayList<Patient> patientsList) {
        for (Patient p : patientsList) {
            model.addRow(new Object[]{p.getPersonalHealthNumber(), p.getLastName(), p.getFirstName()});
        }
        table.setModel(model);
    }
}
