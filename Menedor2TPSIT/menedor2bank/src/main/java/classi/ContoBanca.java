package classi;

public class ContoBanca {

    public double saldo;

    public ContoBanca(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return this.saldo;
    }


    public void aumentaSaldo(double saldo) {
        this.saldo += saldo;
    }

    public void decrementaSaldo(double saldo) {
        this.saldo -= saldo;
    }

    //to string nn serve, uso getSaldo e gestistico sul main

}