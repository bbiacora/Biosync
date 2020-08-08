package ui;

import model.Patients;
import persistence.Reader;
import persistence.Writer;
import ui.panels.RegisterPatientPanel;
import ui.panels.ViewPatientsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BiosyncGUI extends JFrame {
    private static final String PATIENTS_FILE = "./data/patients.txt";
    private static final String FAVICON_IMAGE = "./data/image/favicon.png";
    private Patients patients;

    // Reference: https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
    public BiosyncGUI() {
        super("BIOSYNC");
        frameSetUp();
        menuBarSetUp();

        loadPatients();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel(patients);
        RegisterPatientPanel registerPatientPanel = new RegisterPatientPanel(patients);
        panel.add(viewPatientsPanel);
        panel.add(registerPatientPanel);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES:
    // EFFECTS:
    public void frameSetUp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        ImageIcon favicon = new ImageIcon(FAVICON_IMAGE);
        setIconImage(favicon.getImage());
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: http://zetcode.com/javaswing/menusandtoolbars/
    private void menuBarSetUp() {
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
