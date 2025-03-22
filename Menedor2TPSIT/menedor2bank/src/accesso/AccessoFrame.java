package accesso;

import main.Tools;
import menu.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class AccessoFrame extends JFrame {

    private final JPanel pannello;
    private final GridBagConstraints gbc;
    private final JTextField campoUsername;
    private JPasswordField campoPassword, campoConfermaPassword;
    private final JButton pulsanteAccedi, pulsanteRegistrati;
    private static String[] datiUtente = new String[2];
    private AccessoUtenteMain aU = new AccessoUtenteMain();
    //JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/logo_MenedorBank.png")));
    JLabel logo = new JLabel(new ImageIcon("D:/progettiJava/Menedor2TPSIT/menedor2bank/resource/Logo_MenedorBank.png"));
    //URL imgURL = getClass().getClassLoader().getResource("Logo_MenedorBank.png");

    public AccessoFrame() {
        setTitle("Menedor Bank - Accesso");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        pannello = new JPanel(new GridBagLayout());
        pannello.setBackground(new Color(240, 240, 245));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Logo della banca
        //JLabel logo = new JLabel(new ImageIcon("logo_MenedorBank.png"));

//        if (imgURL != null) {
//            logo = new JLabel(new ImageIcon(imgURL));
//        } else {
//            System.out.println("ERRORE: Immagine non trovata con getClassLoader!");
//        }

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        pannello.add(logo, gbc);

        // Username
        gbc.gridy = 1; gbc.gridwidth = 1;
        pannello.add(new JLabel("Username:"), gbc);
        campoUsername = creaCampoDiTesto("Inserisci il tuo username");
        datiUtente[0] = campoUsername.getText().trim();
        gbc.gridx = 1;
        pannello.add(campoUsername, gbc);

        // Password
        gbc.gridy = 2; gbc.gridx = 0;
        pannello.add(new JLabel("Password:"), gbc);
        campoPassword = new JPasswordField(15);
        datiUtente[1] = campoPassword.getText().trim();
        stileCampo(campoPassword);
        gbc.gridx = 1;
        pannello.add(campoPassword, gbc);

        // Pulsante Login
        pulsanteAccedi = creaPulsante("Accedi", new Color(34, 153, 84));
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        pannello.add(pulsanteAccedi, gbc);

        // Pulsante Registrati
        pulsanteRegistrati = creaPulsante("Registrati", new Color(0, 102, 204));
        gbc.gridy = 4;
        pannello.add(pulsanteRegistrati, gbc);

        // Azioni dei pulsanti
        pulsanteAccedi.addActionListener(e -> login());
        pulsanteRegistrati.addActionListener(e -> registrazione());

        add(pannello);
        setVisible(true);
    }

    private JTextField creaCampoDiTesto(String placeholder) {
        JTextField campo = new JTextField(15);
        stileCampo(campo);
        campo.setText(placeholder);
        campo.setForeground(Color.GRAY);
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
        return campo;
    }

    private void stileCampo(JTextField campo) {
        campo.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        campo.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        campo.setPreferredSize(new Dimension(200, 30));
    }

    private JButton creaPulsante(String testo, Color colore) {
        JButton pulsante = new JButton(testo);
        pulsante.setFont(new Font("Sans Serif", Font.BOLD, 16));
        pulsante.setBackground(colore);
        pulsante.setForeground(Color.WHITE);
        pulsante.setFocusPainted(false);
        pulsante.setBorder(new LineBorder(colore.darker(), 1));
        pulsante.setPreferredSize(new Dimension(200, 40));
        pulsante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pulsante.setBackground(colore.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pulsante.setBackground(colore);
            }
        });
        return pulsante;
    }

    // Metodo per gestire il login
    private void login() {
        String username = campoUsername.getText().trim();
        String password = new String(campoPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username o password non possono essere vuoti", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if (aU.findUtent(username, password)) {
                if(AccessoUtenteMain.findUtent(username, password)){
                    JOptionPane.showMessageDialog(null, "Accesso effettuato!");
                    dispose(); // Procedi con l'accesso
                } else {
                    JOptionPane.showMessageDialog(null, "L'account non esistente", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Metodo per gestire la registrazione
    private void registrazione() {
        pannello.removeAll(); // Rimuove tutti i componenti dal pannello

        // Aggiungiamo i campi per la registrazione
        JTextField nuovoCampoUsername = new JTextField(15);
        JPasswordField nuovoCampoPassword = new JPasswordField(15);
        JPasswordField nuovoCampoConfermaPassword = new JPasswordField(15);

        JLabel usernameLabel = new JLabel("Crea Username:");
        JLabel passwordLabel = new JLabel("Crea Password:");

        // Posiziona gli elementi di registrazione
        gbc.gridx = 0; gbc.gridy = 1;
        pannello.add(usernameLabel);
        pannello.add(usernameLabel);
        datiUtente[0] = usernameLabel.getText();
        gbc.gridy = 2;
        pannello.add(nuovoCampoUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pannello.add(passwordLabel, gbc);
        datiUtente[1] = passwordLabel.getText();
        gbc.gridy = 4;
        pannello.add(nuovoCampoPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        pannello.add(new JLabel("Conferma Password:"), gbc);
        gbc.gridy = 6;
        pannello.add(nuovoCampoConfermaPassword, gbc);

        JButton pulsanteConfermaRegistrazione = creaPulsante("Conferma Registrazione", new Color(0, 153, 0));
        gbc.gridy = 8;
        pannello.add(pulsanteConfermaRegistrazione, gbc);

        pulsanteConfermaRegistrazione.addActionListener(e -> confermaRegistrazione(nuovoCampoUsername, nuovoCampoPassword, nuovoCampoConfermaPassword));

        pannello.revalidate();  // Rende effettive le modifiche al layout
        pannello.repaint();     // Rende visibili i nuovi componenti
    }

    // Metodo per confermare la registrazione
    private void confermaRegistrazione(JTextField nuovoCampoUsername, JPasswordField nuovoCampoPassword, JPasswordField nuovoCampoConfermaPassword) {
        String username = nuovoCampoUsername.getText().trim();
        datiUtente[0] = username;
        String password = new String(nuovoCampoPassword.getPassword()).trim();
        datiUtente[1] = password;
        String confermaPassword = new String(nuovoCampoConfermaPassword.getPassword()).trim();

        if (!Tools.controlloPassword(password) || Tools.controlloNomeUtente(username)) {
            JOptionPane.showMessageDialog(null, "Username non può avere cartteri speciali (3-16 caratteri) e password tra 8 e 16 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.isEmpty() || password.isEmpty() || confermaPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tutti i campi devono essere compilati", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if (!password.equals(confermaPassword)) {
                JOptionPane.showMessageDialog(this, "Le password non coincidono", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                if(AccessoUtenteMain.addUtent(username, password)){
                    JOptionPane.showMessageDialog(null, "Registrazione effettuata!");
                    dispose(); // Procedi con la registrazione
                } else {
                    JOptionPane.showMessageDialog(null, "Account già esistente", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        LocalDate x = LocalDate.now() ;
        LocalDate data = LocalDate.of(x.getYear(),x.getMonthValue(),x.getDayOfMonth());
        AccessoUtenteMain.addInfo(AccessoFrame.getDatiUtente()[0]+".csv", MainFrame.getPortafoglio(),MainFrame.getContoBanca(),data);
        //JOptionPane.showMessageDialog(this, "Registrazione avvenuta con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        //dispose(); // Chiude la finestra di registrazione e mostra il login
    }

    public static String[] getDatiUtente() {
        return datiUtente;
    }
}
