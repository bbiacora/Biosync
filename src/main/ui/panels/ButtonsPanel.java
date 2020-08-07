package ui.panels;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        FlowLayout flowLayout = new FlowLayout();
        JButton removeButton = new JButton("Remove");
        JButton updateButton = new JButton("Update");

        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(updateButton, flowLayout);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(removeButton, flowLayout);
    }
}
