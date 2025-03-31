public class TaPenger implements Runnable {
    Bank bankreferanse;
    int mengde;

    public TaPenger(Bank bankref,int mengde){
        this.bankreferanse = bankref;
        this.mengde = mengde;
    }

    @Override
    public void run(){
        for (int i= 0; i<10;i++){
            bankreferanse.ta(this.mengde);
            System.out.println(bankreferanse.saldo());
            bankreferanse.gi(this.mengde);
        }
    }
}
