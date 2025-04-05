import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
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
        //merEnnTo.signalAll(); Siden vi ikke bruker await lenger i nye metoden kan vi ikke ha at denne sender singalAll
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

    public Frekvenstabell[] taUtTo(CountDownLatch latch){ // modifisert metode
        this.tabellLock.lock();
        try {
            if (this.antall() < 2){
                // merEnnTo.await(); endret denne linjen for å bli mere effektiv
                // forklaring: Hvis noen av trådene treffer at tabeller < 2 betyr det at vi er nesten ferdig 
                // eller ferdig med mergingen, det betyr at vi kan heller lukke trådene med en countdownlatch 
                // dette gjør jeg ved å returnere null opp til tråden
                return null;

            }
            Frekvenstabell[] frekvenstabell = new Frekvenstabell[2];
            frekvenstabell[0] = this.taUt();
            frekvenstabell[1] = this.taUt();
            return frekvenstabell;
        }
        finally {
            tabellLock.unlock();
        }
    }

    public void skrivTilFil(String filnavn){
        subregister.makeFile(filnavn);
    }
}
