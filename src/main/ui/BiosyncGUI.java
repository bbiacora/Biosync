package ui;

import model.Patients;
import persistence.Reader;
import persistence.Writer;
import ui.gui.components.LoadingPanel;
import ui.gui.components.PatientRegistrationPanel;
import ui.gui.components.ViewPatientsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

// Represents a patient management application
public class BiosyncGUI extends JFrame {
    private static final String PATIENTS_FILE = "./data/patients.txt";
    private static final String FAVICON = "./data/image/favicon.png";
    private static final String SAVE_ICON = "./data/image/iconSave.png";

    private Patients patients;

    // MODIFIES: this
    // EFFECTS: constructs BiosyncGUI, loads patients, displays LoadingPanel
    // Reference: https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
    //            https://stackoverflow.com/a/1614803 (change favicon)
    public BiosyncGUI() {
        super("BIOSYNC");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        ImageIcon favicon = new ImageIcon(FAVICON);
        this.setIconImage(favicon.getImage());

        menuBarSetUp();

        loadPatients();

        JPanel home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));
        home.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        LoadingPanel loadingPanel = new LoadingPanel();
        home.add(loadingPanel);

        this.add(home);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loadNextPanel(home, loadingPanel);
    }

    // MODIFIES: this
    // EFFECTS: constructs a menu bar with 2 menu items (save and exit);
    //          saves the state of BiosyncGUI when save menu item is clicked,
    //          stops and close BiosyncGUI window when exit menu item is clicked
    // Reference: http://zetcode.com/javaswing/menusandtoolbars/
    private void menuBarSetUp() {
        ImageIcon iconSave = new ImageIcon(SAVE_ICON);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveMenuItem = new JMenuItem("Save", iconSave);
        saveMenuItem.addActionListener((event) -> savePatients());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: replaces loadingPanel with viewPatientsPanel and patientRegistrationPanel after 7000ms
    // Reference: https://stackoverflow.com/a/11613540 (execute a timed event)
    private void loadNextPanel(JPanel panel, JPanel loadingPanel) {
        ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel(patients);
        PatientRegistrationPanel patientRegistrationPanel = new PatientRegistrationPanel(patients);

        Timer timer = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(loadingPanel);
                validate();
                panel.add(viewPatientsPanel);
                panel.add(patientRegistrationPanel);
                pack();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: saves all patient's information in patients to PATIENTS_FILE
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void savePatients() {
        try {
            Writer writer = new Writer(new File(PATIENTS_FILE));
            writer.write(patients);
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println(PATIENTS_FILE + " not found.");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads patients from PATIENTS_FILE if the files exists, otherwise instantiates patients
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void loadPatients() {
        try {
            patients = Reader.readPatients(new File(PATIENTS_FILE));
        } catch (IOException e) {
            patients = new Patients();
        }
    }
}
