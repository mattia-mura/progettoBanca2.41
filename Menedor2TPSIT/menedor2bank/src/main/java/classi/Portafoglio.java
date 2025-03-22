package classi;

public class Portafoglio {

    private double schei;

    public Portafoglio(double schei) {
        this.schei = schei;
    }

    public double getSchei() {
        return this.schei;
    }


    public void aumentaSchei(double schei) {
        this.schei += schei;
    }

    public void decrementaSchei(double schei) {
        this.schei -= schei;
    }

    //to string nn serve, uso getSchei e gestistico sul main

}