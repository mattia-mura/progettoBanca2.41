package main;

public class Investimenti {

    private int durata;
    private double guadagno;


    public Investimenti(int durata,double guadagno){
        this.durata=durata;
        this.guadagno=guadagno;
    }

    public void scalaTempo (){
        this.durata = this.durata-1;
    }

    public int getTempo(){
        return this.durata;
    }

    public double getGuadagno(){
        return this.guadagno;
    }
}
