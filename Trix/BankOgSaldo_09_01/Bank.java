import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Bank{
    ReentrantLock banklock;
    Condition notEmpty;
    int pengebeløp;

    public Bank(int startSaldo){
        this.pengebeløp = startSaldo;
        this.banklock = new ReentrantLock();
        notEmpty = this.banklock.newCondition();
    }
    public void gi(int beløp){
        banklock.lock();
        try {
            this.pengebeløp += beløp;
            notEmpty.signalAll();
        }
        finally {
            banklock.unlock();
        }
    }
    public void ta(int beløp){
        banklock.lock();
        try {
            while (beløp > this.pengebeløp){
                notEmpty.await();
            }
            this.pengebeløp -= beløp;
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException");
        }
        finally{
            banklock.unlock();
        }

    }
    public int saldo(){
        return this.pengebeløp;
    }
}