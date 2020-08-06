package ui;

import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PatientsTable extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    String[] headers = {"Personal Health Number", "Last name", "First Name"};
    Object[][] data = new Object[100][100];

    public PatientsTable(ArrayList<Patient> patientsList) {
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        scroll = new JScrollPane(table);

        showPatientsInTable(patientsList);
        add(scroll, BorderLayout.CENTER);
    }

    // Reference: https://stackoverflow.com/questions/20012772/how-to-populate-a-jtable-from-an-arraylist
    public void showPatientsInTable(ArrayList<Patient> patientsList) {
        for (Patient p : patientsList) {
            model.addRow(new Object[]{p.getPersonalHealthNumber(), p.getLastName(), p.getFirstName()});
        }
    }
}
