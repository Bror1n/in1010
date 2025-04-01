import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;



public class Monitor {
    private Subsekvensregister subregister;
    private ReentrantLock tabellLock = new ReentrantLock();
    private Condition merEnnTo = tabellLock.newCondition();

    
    public Monitor(){
        subregister = new Subsekvensregister();
    }

    public void settInn(Frekvenstabell f){
        subregister.settInn(f);
        merEnnTo.signalAll();
    }

    public Frekvenstabell taUt(){
        return subregister.taUt();
    }

    public int antall(){
        return subregister.antall();
    }

    public static Frekvenstabell les(String filnavn){
        return Subsekvensregister.les(filnavn);
    }

    public Frekvenstabell[] taUtTo(){
        this.tabellLock.lock();
        try {
            while (this.antall() < 2){
                merEnnTo.await();
            }
            Frekvenstabell[] frekvenstabell = new Frekvenstabell[2];
            frekvenstabell[0] = this.taUt();
            frekvenstabell[1] = this.taUt();
            return frekvenstabell;
        }
        catch (InterruptedException e){
            System.out.println("Thread was interrupted");
            return null;
        }
        finally {
            tabellLock.unlock();
        }
    }
}
