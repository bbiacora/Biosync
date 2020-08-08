package ui.panels;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private static final String LOGO = "./data/image/logo.png";
    private static final String LOADING = "./data/image/loading.gif";

    public HomePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(30, 50, 15, 50);
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        ImageIcon logo = new ImageIcon(LOGO);
        JLabel loading = new JLabel(logo);
        add(loading, constraints);
        constraints.gridy = 1;
        Icon imgIcon = new ImageIcon(LOADING);
        JLabel label = new JLabel(imgIcon);
        add(label, constraints);

//        constraints.gridy = 1;
//        JButton load = new JButton("Load");
//        add(load, constraints);
//        load.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                constraints.gridx = 1;
//                constraints.gridy = 2;
//
//            }
//        });
    }


}
