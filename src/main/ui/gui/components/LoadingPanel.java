package ui.gui.components;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {
    private static final String LOGO = "./data/image/logo.png";
    private static final String BUFFER_IMAGE = "./data/image/buffer.gif";

    // MODIFIES:
    // EFFECTS:
    public LoadingPanel() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(77, 77, 40, 77);

        constraints.gridy = 0;
        JLabel bufferImage = new JLabel(new ImageIcon(LOGO));
        bufferImage.setForeground(new Color(238, 238, 238));
        this.add(bufferImage, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(0, 77, 63, 77);
        JLabel buffer = new JLabel(new ImageIcon(BUFFER_IMAGE));
        this.add(buffer, constraints);
    }
}


