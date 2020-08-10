package ui.gui.components;

import model.Patient;

import javax.swing.*;
import java.awt.*;

public class SelectedPatientDialog extends JDialog {
    private static final String FAVICON = "./data/image/favicon.png";

    private int width = 600;
    private int height = 443;
    private Patient patient;

    // MODIFIES:
    // EFFECTS:
    public SelectedPatientDialog(Patient patient) {
        this.setTitle("BIOSYNC - " + patient.getFirstName().toUpperCase() + " " + patient.getLastName().toUpperCase());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setSize(width, height);

        ImageIcon favicon = new ImageIcon(FAVICON);
        this.setIconImage(favicon.getImage());

        this.setLocationRelativeTo(null);
    }
}
