package ui.panels;

import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewPatientsPanel extends JPanel {
    private static final String[] HEADERS = {"Personal Health Number", "Last name", "First Name"};
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;

    public ViewPatientsPanel(ArrayList<Patient> patientsList) {
        tableSetUp(patientsList);
//        buttonsSetUp();
    }

    // Reference: https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
    private void tableSetUp(ArrayList<Patient> patientsList) {
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(600, 100));
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(HEADERS);
        showPatientsInTable(table, patientsList);
        scroll = new JScrollPane(table);
        add(scroll);
    }

//    private void buttonsSetUp() {
//        FlowLayout flowLayout = new FlowLayout();
//        JButton removeButton = new JButton("Remove");
//        JButton updateButton = new JButton("Update");
//
//        flowLayout.setAlignment(FlowLayout.LEFT);
//        add(removeButton, flowLayout);
//        flowLayout.setAlignment(FlowLayout.RIGHT);
//        add(updateButton, flowLayout);
//    }

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
