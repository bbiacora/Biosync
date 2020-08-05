package ui;

import model.Patients;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BiosyncGUI extends JFrame {
    private static final String PATIENTS_FILE = "./data/patients.txt";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private Patients patients;

    public BiosyncGUI() {
        super("BIOSYNC");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        createMenuBar();

        loadPatients();

        setVisible(true);
    }

    // Reference: http://zetcode.com/javaswing/menusandtoolbars/
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener((event) -> savePatients());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // EFFECTS: saves all patient's information in patients to PATIENTS_FILE
    // Refernce: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void savePatients() {
        try {
            Writer writer = new Writer(new File(PATIENTS_FILE));
            writer.write(patients);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(PATIENTS_FILE + " not found.");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads patients from PATIENTS_FILE, if file exists;
    //          otherwise instantiates patients
    // Refernce: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void loadPatients() {
        try {
            patients = Reader.readPatients(new File(PATIENTS_FILE));
        } catch (IOException e) {
            patients = new Patients();
        }
    }
}
