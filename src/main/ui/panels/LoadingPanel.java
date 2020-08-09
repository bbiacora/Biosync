package ui.panels;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {

    private static final String LOGO = "./data/image/logo.png";
    private static final String BUFFER_IMAGE = "./data/image/buffer.gif";

    private GridBagConstraints constraints;

    public LoadingPanel() {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(50, 50, 50, 50);
        constraints.gridy = 0;
        JLabel loading = new JLabel(new ImageIcon(LOGO));
        loading.setForeground(new Color(238, 238, 238));
        add(loading, constraints);
        constraints.gridy = 1;
        constraints.insets.top = 0;
        JLabel buffer = new JLabel(new ImageIcon(BUFFER_IMAGE));
        add(buffer, constraints);
    }
}
