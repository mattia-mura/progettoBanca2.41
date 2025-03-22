package menu;

import main.ContoBanca;
import main.Main;
import accesso.AccessoUtenteMain;
import accesso.AccessoFrame;
import main.Portafoglio;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import accesso.AccessoFrame;

public class MainFrame extends JFrame {

    String nome =AccessoFrame.getDatiUtente()[0];
    private int giorno = (int) AccessoUtenteMain.getLastSomething(nome,2);
    private int mese = (int) AccessoUtenteMain.getLastSomething(nome,3);
    private int anno = (int) AccessoUtenteMain.getLastSomething(nome,4);

    private LocalDate data = LocalDate.of(giorno,mese,anno);

    private JPanel pannello;
    private GridBagConstraints gbc;
    private JLabel etichettaPortafoglio;
    private JLabel etichettaBanca;
    private JLabel etichettaData;
    private JButton bottoneDeposita;
    private JButton bottonePreleva;
    private JButton bottoneInvesti;
    private JButton bottoneTransizioni;
    private JButton bottoneAvanzaMese;
    private JButton bottoneSalvaEEsci;
    private String[] datiUtente;

    private Portafoglio portafoglioSolidi = new Portafoglio(0);
    private ContoBanca contoVirtuale = new ContoBanca(0);

    JLabel logo = new JLabel(new ImageIcon("E:/Menedor2.1/tpsit 2 nuovissimo/menedor2bestgoat/src/main/resources/Logo_MenedorBank.png"));

    int nMaxInvestimenti = 5;
    int nInvestimenti = 0;
    int tempInvestimento[] = new int [nMaxInvestimenti]; //mesi mancanti alla fine dell'investimento.
    double Investimenti[] = new double [nMaxInvestimenti]; //contiene gli investimentin fino al suo return

    int probabilitaGuadagno = 0;
    int percentualeMinMaxGuadagno[] = new int[2];
    int percentualeMinMaxPerdita[] = new int[2];

    public MainFrame(String[] datiUtente) {
        this.datiUtente = datiUtente;

        // Impostazioni finestra
        setTitle("Menedor Bank - Home");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);  // Finestra di dimensioni fisse

        // Crea pannello con miglioramenti stilistici
        pannello = new JPanel();
        pannello.setLayout(new GridBagLayout());
        pannello.setBackground(new Color(240, 240, 240));

        // Crea GridBagConstraints
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10); // Padding attorno agli elementi
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Etichette per mostrare saldi e data
        etichettaPortafoglio = new JLabel("Soldi solidi: " + portafoglioSolidi.getSchei());
        etichettaBanca = new JLabel("Soldi virtuali: " + contoVirtuale.getSaldo());
        etichettaData = new JLabel("Data: " + data);

        JLabel[] etichette = {etichettaPortafoglio, etichettaBanca, etichettaData};
        for (JLabel etichetta : etichette) {
            etichetta.setForeground(new Color(50, 50, 50));
            etichetta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        // Bottone con miglioramenti stilistici
        bottoneDeposita = creaBottoneStilizzato("DEPOSITA");
        bottonePreleva = creaBottoneStilizzato("PRELIEVA");
        bottoneInvesti = creaBottoneStilizzato("INVESTI");
        bottoneTransizioni = creaBottoneStilizzato("TRANSIZIONI");
        bottoneAvanzaMese = creaBottoneStilizzato("AVANZA MESE");
        bottoneSalvaEEsci = creaBottoneStilizzato("SALVA ED ESCI");

        // Azioni dei bottoni
        bottoneDeposita.addActionListener(e -> deposita());
        bottonePreleva.addActionListener(e -> prelieva());
        bottoneInvesti.addActionListener(e -> nuovoInvestimento());
        bottoneTransizioni.addActionListener(e -> transizioni());
        bottoneAvanzaMese.addActionListener(e -> avanzaMese());
        bottoneSalvaEEsci.addActionListener(e -> salvaEEsci());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridwidth = 1;  // Reset gridwidth

        // Aggiungi etichette al pannello
        gbc.gridy = 1;
        gbc.gridx = 0;
        pannello.add(etichettaPortafoglio, gbc);
        gbc.gridx = 1;
        pannello.add(etichettaBanca, gbc);
        gbc.gridx = 2;
        pannello.add(etichettaData, gbc);

        // Aggiungi bottoni al pannello
        gbc.gridy = 2;
        gbc.gridx = 0;
        pannello.add(bottoneDeposita, gbc);
        gbc.gridx = 1;
        pannello.add(logo, gbc);
        gbc.gridx = 2;
        pannello.add(bottonePreleva, gbc);


        gbc.gridy = 3;
        gbc.gridx = 0;
        pannello.add(bottoneTransizioni, gbc);
        gbc.gridx = 1;
        pannello.add(bottoneInvesti, gbc);
        gbc.gridx = 2;
        pannello.add(bottoneAvanzaMese, gbc);

        // Bottone salva ed esci
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        pannello.add(bottoneSalvaEEsci, gbc);

        // Aggiungi pannello alla finestra
        add(pannello);
        setVisible(true);
    }


    // Crea un bottone stilizzato con effetti hover
    private JButton creaBottoneStilizzato(String testo) {
        JButton bottone = new JButton(testo);
        bottone.setFont(new Font("Segoe UI", Font.BOLD, 16));
        bottone.setBackground(new Color(38, 38, 38));
        bottone.setForeground(Color.white);
        bottone.setFocusPainted(false);
        bottone.setBorder(new LineBorder(new Color(58, 58, 58), 2));
        bottone.setPreferredSize(new Dimension(180, 40));
        bottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        bottone.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bottone.setBackground(new Color(58, 58, 58));
            }

            public void mouseExited(MouseEvent e) {
                bottone.setBackground(new Color(38, 38, 38));
            }
        });

        return bottone;
    }

    private void mostraMessaggio(String messaggio, boolean successo) {
        JOptionPane.showMessageDialog(this, messaggio, successo ? "Successo" : "Errore",
                successo ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    // Gestisci transazioni (deposito/prelievo)
    private void gestisciTransazione(int tipo) {
        String input = JOptionPane.showInputDialog(this, tipo == 1 ? "Deposita:" : "Prelieva:", "0");
        try {
            double importo = Double.parseDouble(input);
            boolean successo = depositPreleva(tipo, importo, portafoglioSolidi, contoVirtuale);
            mostraMessaggio(successo ? "Operazione completata" : "Operazione fallita", successo);
        } catch (NumberFormatException e) {
            mostraMessaggio("Inserisci un numero valido.", false);
        }
    }

    // Gestisci operazione di deposito
    public void deposita() {
        gestisciTransazione(1);
        aggiornaUI();
    }

    // Gestisci operazione di prelievo
    public void prelieva() {
        gestisciTransazione(2);
        aggiornaUI();
    }

    // Nuova operazione di investimento
    public void nuovoInvestimento() {
        InvestiFrame frameInvestimento = new InvestiFrame();
        contoVirtuale.decrementaSaldo(frameInvestimento.confermaInvestimento());
        aggiornaUI();
    }

    // Aggiorna UI con nuovi dati
    private void aggiornaUI() {
        etichettaPortafoglio.setText("Soldi solidi: " + portafoglioSolidi.getSchei());
        etichettaBanca.setText("Soldi virtuali: " + contoVirtuale.getSaldo());
        etichettaData.setText("Data: " + data);
        pannello.revalidate();
        pannello.repaint();
    }

    // Operazione di avanzamento mese
    public void avanzaMese() {
        portafoglioSolidi.aumentaSchei(100);
        data = data.plusMonths(1); // Avanza la data di 1 mese
        /*
        double[] array = InvestiFrame.trovaInvestimenti();
        for (int i=0;i<5;i++){
            if (array[i] != 0){
                //stampa e fai investimenti
            }

        }
        */

        aggiornaUI();
    }

    // Placeholder per altre operazioni
    public void transizioni() {}

    // Salva ed esci dall'applicazione
    public void salvaEEsci() {
        if (AccessoUtenteMain.addInfo(AccessoFrame.getDatiUtente()[0]+".csv", portafoglioSolidi,contoVirtuale,data)){
            //dati salvati
        }else{
            //dati non salvati
        }

        dispose();
    }


    public static boolean depositPreleva (int scelta, double money, Portafoglio portafoglio, ContoBanca contoBancario) {
        if (scelta == 1) {
            if ( (money > portafoglio.getSchei() ) || (money<=0) ){return false;}
            portafoglio.decrementaSchei(money);
            contoBancario.aumentaSaldo(money);
        }else {
            if ( (money > portafoglio.getSchei() ) || (money<=0) ){return false;}
            portafoglio.aumentaSchei(money);
            contoBancario.decrementaSaldo(money);

        }
        return true;

    }

}
