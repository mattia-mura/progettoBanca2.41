package main;

import javax.swing.*;
import java.awt.*;

public class Transizioni extends JFrame {


        private final JPanel pannello;
        private final GridBagConstraints gbc;

        public Transizioni(){

            setTitle("Menedor Bank - Trandizioni");
            setSize(500, 650);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(true);

            pannello = new JPanel(new GridBagLayout());
            pannello.setBackground(new Color(240, 240, 240));

            gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;



            add(pannello);
            setVisible(true);
        }
 }

