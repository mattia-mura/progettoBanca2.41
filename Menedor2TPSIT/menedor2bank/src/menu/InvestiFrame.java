package menu;

import accesso.RoundedButton;
import main.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class InvestiFrame extends JFrame {

    private int durataInvestimento = -1;
    private int livelloRischio = -1;
    private double importoInvestimento = 0;

    int nMaxInvestimenti = 5;
    int nInvestimenti = 0;
    int tempInvestimento[] = new int [nMaxInvestimenti]; //mesi mancanti alla fine dell'investimento.
    double Investimenti[] = new double [nMaxInvestimenti]; //contiene gli investimentin fino al suo return

    int probabilitaGuadagno = 0;
    int percentualeMinMaxGuadagno[] = new int[2];
    int percentualeMinMaxPerdita[] = new int[2];

    private final JPanel pannello;
    private final GridBagConstraints gbc;

    private final JButton breveDurata;
    private final JButton mediaDurata;
    private final JButton lungaDurata;

    private final JButton bassoRischio;
    private final JButton medioRischio;
    private final JButton altoRischio;

    private final JButton annulla;
    private final JButton confermaInvestimento;

    private final JLabel etichettaImporto;
    private final JTextField campoImporto;

    JLabel logo = new JLabel(new ImageIcon("E:/Menedor2.1/tpsit 2 nuovissimo/menedor2bestgoat/src/main/resources/Logo_MenedorBank.png"));

    public InvestiFrame() {
        setTitle("Menedor Bank - Investimenti");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        pannello = new JPanel(new GridBagLayout());
        pannello.setBackground(new Color(240, 240, 240));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        //logo = caricaLogo();

        // Creazione bottoni
        breveDurata = creaBottone("Breve Durata");
        mediaDurata = creaBottone("Media Durata");
        lungaDurata = creaBottone("Lunga Durata");

        bassoRischio = creaBottone("Basso Rischio");
        medioRischio = creaBottone("Medio Rischio");
        altoRischio = creaBottone("Alto Rischio");

        annulla = creaBottoneArrotondato("Annulla", new Color(200, 50, 50));
        confermaInvestimento = creaBottoneArrotondato("Investi", new Color(0, 153, 0));

        // Etichetta e campo per l'importo
        etichettaImporto = new JLabel("Importo da investire:");
        etichettaImporto.setFont(new Font("Sans Serif", Font.BOLD, 20));
        etichettaImporto.setHorizontalAlignment(SwingConstants.CENTER);

        campoImporto = new JTextField(15);
        campoImporto.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        // Assegna azioni ai bottoni
        breveDurata.addActionListener(e -> {
            durataInvestimento = 1;
            selezionaDurata();
        });

        mediaDurata.addActionListener(e -> {
            durataInvestimento = 2;
            selezionaDurata();
        });

        lungaDurata.addActionListener(e -> {
            durataInvestimento = 3;
            selezionaDurata();
        });


        bassoRischio.addActionListener(e -> selezionaRischio(1));
        medioRischio.addActionListener(e -> selezionaRischio(2));
        altoRischio.addActionListener(e -> selezionaRischio(3));


        annulla.addActionListener(e -> dispose());
        confermaInvestimento.addActionListener(e -> confermaInvestimento());

        // Mostra il menu principale
        mostraMenuIniziale();

        add(pannello);
        setVisible(true);
    }

//    private JLabel caricaLogo() {
//        URL logoPath = getClass().getClassLoader().getResource("Logo_MenedorBank.png");
//        if (logoPath != null) {
//            return new JLabel(new ImageIcon(logoPath));
//        } else {
//            System.err.println("⚠ Immagine del logo non trovata!");
//            return new JLabel("Menedor Bank");
//        }
//    }

    private JButton creaBottone(String testo) {
        JButton bottone = new JButton(testo);
        bottone.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        bottone.setBackground(new Color(219, 219, 219));
        bottone.setForeground(Color.BLACK);
        bottone.setFocusPainted(false);
        bottone.setBorder(new LineBorder(Color.BLACK, 1));
        bottone.setPreferredSize(new Dimension(200, 40));

        bottone.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bottone.setBackground(new Color(200, 200, 200));
            }
            public void mouseExited(MouseEvent e) {
                bottone.setBackground(new Color(219, 219, 219));
            }
        });

        return bottone;
    }

    private JButton creaBottoneArrotondato(String testo, Color colore) {
        JButton bottone = new RoundedButton(testo);
        bottone.setFont(new Font("Sans Serif", Font.BOLD, 16));
        bottone.setBackground(colore);
        bottone.setForeground(Color.WHITE);
        bottone.setFocusPainted(false);
        bottone.setBorder(new LineBorder(Color.BLACK, 1));
        bottone.setPreferredSize(new Dimension(120, 40));

        return bottone;
    }

    private void mostraMenuIniziale() {
        pulisciPannello();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        pannello.add(logo, gbc);

        gbc.gridy = 1;
        pannello.add(new JLabel("Seleziona la durata dell'investimento:"), gbc);

        gbc.gridy = 2;
        pannello.add(breveDurata, gbc);

        gbc.gridy = 3;
        pannello.add(mediaDurata, gbc);

        gbc.gridy = 4;
        pannello.add(lungaDurata, gbc);

        pannello.revalidate();
        pannello.repaint();
    }

    private void selezionaDurata() {
        pulisciPannello();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        pannello.add(logo, gbc);

        gbc.gridy = 1;
        pannello.add(new JLabel("Seleziona il livello di rischio:"), gbc);

        gbc.gridy = 2;
        pannello.add(bassoRischio, gbc);

        gbc.gridy = 3;
        pannello.add(medioRischio, gbc);

        gbc.gridy = 4;
        pannello.add(altoRischio, gbc);

        pannello.revalidate();
        pannello.repaint();
    }

    private void selezionaRischio(int rischio) {
        livelloRischio = rischio;
        pulisciPannello();

        gbc.gridx = 0;
        gbc.gridy = 0;
        pannello.add(etichettaImporto, gbc);

        gbc.gridy = 1;
        pannello.add(campoImporto, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pannello.add(confermaInvestimento, gbc);

        gbc.gridx = 1;
        pannello.add(annulla, gbc);

        pannello.revalidate();
        pannello.repaint();
    }

    private void indirizzoInvestimenti(){

        if (durataInvestimento == -1 || livelloRischio == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona durata e rischio prima di confermare l'investimento.", "Errore", JOptionPane.ERROR_MESSAGE);
            dispose();
        }

        if (durataInvestimento == 1 ) {

            tempInvestimento [nInvestimenti]= 1;

        } else {
            if (durataInvestimento == 2 ) {

                tempInvestimento [nInvestimenti]= 5;

            } else {

                tempInvestimento [nInvestimenti]= 10;

            }
        }

        if (livelloRischio == 1 ) {

            probabilitaGuadagno = 95;
            percentualeMinMaxGuadagno[0] = 5;
            percentualeMinMaxGuadagno[1] = 10;
            percentualeMinMaxPerdita[0] = 5;
            percentualeMinMaxPerdita[1] = 8;

        } else {
            if (livelloRischio == 2 ) {

                probabilitaGuadagno = 60;
                percentualeMinMaxGuadagno[0] = 20;
                percentualeMinMaxGuadagno[1] = 30;
                percentualeMinMaxPerdita[0] = 15;
                percentualeMinMaxPerdita[1] = 25;

            } else {

                probabilitaGuadagno = 40;
                percentualeMinMaxGuadagno[0] = 60;
                percentualeMinMaxGuadagno[1] = 85;
                percentualeMinMaxPerdita[0] = 50;
                percentualeMinMaxPerdita[1] = 125;

            }
        }

    }

    public double confermaInvestimento() {
        // Verifica che durata e rischio siano stati selezionati
        if (durataInvestimento == -1 || livelloRischio == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona prima durata e livello di rischio.", "Errore", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
        double soldi = 0.0;
        try {
            soldi = Double.parseDouble(campoImporto.getText());
            if (soldi > 0 /*|| soldi < contoVirtuale*/) {
                indirizzoInvestimenti(); // Ora può essere chiamato in sicurezza
                importoInvestimento = soldi;
                JOptionPane.showMessageDialog(this, "Investimento confermato: " + soldi + "€", "Successo", JOptionPane.INFORMATION_MESSAGE);
                investimentoo(soldi, probabilitaGuadagno, percentualeMinMaxGuadagno, percentualeMinMaxPerdita);
                return soldi;
            } else {
                JOptionPane.showMessageDialog(this, "L'importo deve essere maggiore di 0.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        MainFrame.depositPreleva(2,soldi,MainFrame.getPortafoglio(),MainFrame.getContoBanca());
        return soldi;
    }

    private void pulisciPannello() {
        pannello.removeAll();
        pannello.revalidate();
        pannello.repaint();
    }

    public double investimentoo (double capitale, int probabilitaGuadagno, int rangeGuadagno[],int rangePerdita[]) {


        int probabilita = (int) (Math.random() * 100 + 1);

        if (probabilita <= probabilitaGuadagno) {
            int percentualeGuadagno = (int) (Math.random() * (rangeGuadagno[1] - rangeGuadagno[0] + 1)
                    + rangeGuadagno[0]);
            capitale *= 1 + (double) percentualeGuadagno / 100;
        } else {
            int percentualePerdita = (int) (Math.random() * (rangePerdita[1] - rangePerdita[0] + 1) + rangePerdita[0]);
            capitale *= 1 - (double) percentualePerdita / 100;
        }

        return capitale;
    }
    /*
    public static double[] trovaInvestimenti (){
        double ar[] = new double [nMaxInvestimenti];
        int nInvestimentitrovati = 0;
        for (int i=0;i<nMaxInvestimenti;i++){
            tempInvestimento[i] --;
            if (tempInvestimento[i] == 0){
                ar[nInvestimentitrovati] = Investimenti [i];
                nInvestimentitrovati++;
                for (int j=i;j<nMaxInvestimenti;j++){
                    Investimenti[i]=Investimenti[i+1];
                    tempInvestimento[i]=tempInvestimento[i+1];
                }
                Investimenti [nMaxInvestimenti-1] = 0;
                tempInvestimento [nMaxInvestimenti-1] = 0;
            }
        }
        return ar;
    }
    */


}

