package accesso;

import main.ContoBanca;
import main.Portafoglio;
import menu.MainFrame;

import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class AccessoUtenteMain {

    private static final String name = "CartellaUtenti/";

    public AccessoUtenteMain() {

        File CartellaUtenti = new File(name);
        if ( !CartellaUtenti.exists() ) {CartellaUtenti.mkdir();}
    }

    public static boolean addUtent(String nomeUtente,String password) { // controlla password e nomeUtente --> solo caratteri e numeri , no spazi
        File Utente = new File(name+nomeUtente+".txt");
        File Utente2 = new File(name+nomeUtente+".csv");
        BufferedWriter BW = null;
        BufferedWriter bw = null;
        if (Utente.exists() || Utente2.exists() ) { return false;}
        try {
            BW = new BufferedWriter (new FileWriter( Utente,true ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Utente.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BW.write(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Utente2.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw = new BufferedWriter (new FileWriter( Utente2,true ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LocalDate x = LocalDate.now() ;
        String s= "0.0;0.0;"+x.getDayOfMonth()+";"+x.getMonthValue()+";"+x.getYear()+";";
        try {
            bw.write(s+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BW.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean findUtent(String nomeUtente,String password) {
        File Utente = new File(name+nomeUtente+".txt");
        if ( Utente.exists() ) {
            try ( BufferedReader BW = new BufferedReader (new FileReader( Utente ) ) ){
                String passwordUtent = BW.readLine();
                if( passwordUtent.equals(password) ) {return true;}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }//accesso

    public static boolean addInfo (String nomeUtente, Portafoglio portafoglio, ContoBanca contoBanca, LocalDate localDate) {
        File Utente = new File(name+nomeUtente+".txt");

        if ( Utente.exists() ) {


            try {
                BufferedWriter BW = new BufferedWriter(new FileWriter(nomeUtente + ".csv", true));

                String s = portafoglio.getSchei()+";"+contoBanca.getSaldo()+";"+ localDate.getDayOfMonth()+";"+localDate.getMonthValue()+";"+ localDate.getYear()+";";

                BW.write(s+"\n") ;//cosa devi scrivere


                BW.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }//addInfo

    public static double[] getLastMoney(String nomeUtente){
        File f = new File (name+nomeUtente+".csv");
        //if (!f.exists()){return -1;}
        Scanner scanner;
        if (!f.exists()) {
            throw new RuntimeException("File not found: " + f.getAbsolutePath());
        }
        try {
            scanner = new Scanner(new FileReader(f));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + f.getAbsolutePath(), e);
        }
        double x[] = new double [3];
        while(scanner.hasNextLine()){
            String riga = scanner.nextLine();
            if (!scanner.hasNextLine()){
                String dati[]= riga.split(";");
                for (int i=0;i<2;i++) {
                    x[i] = Double.valueOf(dati[i]);
                }
            }
        }
        return x;
    }

    public static int[] getLastTime(String nomeUtente){
        File f = new File (name+nomeUtente+".csv");
        //if (!f.exists()){return -1;}
        Scanner scanner;
        if (!f.exists()) {
            throw new RuntimeException("File not found: " + f.getAbsolutePath());
        }
        try {
            scanner = new Scanner(new FileReader(f));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + f.getAbsolutePath(), e);
        }
        int x[] = new int [3];
        while(scanner.hasNextLine()){
            String riga = scanner.nextLine();
            if (!scanner.hasNextLine()){
                String dati[]= riga.split(";");
                for (int i=2;i<5;i++) {
                    x[i] = Integer.valueOf(dati[i]);
                }
            }
        }
        return x;
    }


}//class

