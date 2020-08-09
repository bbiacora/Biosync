package ui;

import model.Patients;
import persistence.Reader;
import persistence.Writer;
import ui.panels.LoadingPanel;
import ui.panels.RegisterPatientPanel;
import ui.panels.ViewPatientsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BiosyncGUI extends JFrame {

    private static final String PATIENTS_FILE = "./data/patients.txt";
    private static final String FAVICON = "./data/image/favicon.png";
    private static final String SAVE_ICON = "./data/image/iconSave.png";

    private Patients patients;

    // MODIFIES:
    // EFFECTS:
    // Reference: https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
    public BiosyncGUI() {
        super("BIOSYNC");
        frameSetUp();
        menuBarSetUp();

        loadPatients();

        JPanel home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));
        home.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        LoadingPanel loadingPanel = new LoadingPanel();
//        ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel(patients);
//        RegisterPatientPanel registerPatientPanel = new RegisterPatientPanel(patients);
        home.add(loadingPanel);
//        panel.add(viewPatientsPanel);
//        panel.add(registerPatientPanel);
        add(home);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        timed(home, loadingPanel);
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/questions/1614772/how-to-change-jframe-icon
    private void frameSetUp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        ImageIcon favicon = new ImageIcon(FAVICON);
        setIconImage(favicon.getImage());
    }

    // MODIFIES:
    // EFFECTS:
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
        setJMenuBar(menuBar);
    }

    // MODIFIES:
    // EFFECTS:
    // Reference: https://stackoverflow.com/a/11613540
    private void timed(JPanel panel, JPanel loadingPanel) {
        ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel(patients);
        RegisterPatientPanel registerPatientPanel = new RegisterPatientPanel(patients);
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(loadingPanel);
                validate();
                panel.add(viewPatientsPanel);
                panel.add(registerPatientPanel);
                add(panel);
                pack();
                setLocationRelativeTo(null);
                setVisible(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
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
