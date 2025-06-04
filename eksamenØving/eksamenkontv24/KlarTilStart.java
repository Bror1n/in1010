import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class KlarTilStart {
    ArrayList<Seilfly> kø = new ArrayList<>();
    ReentrantLock bane = new ReentrantLock();
    Condition ventefly = bane.newCondition();

    public void seilflyklar(Seilfly fly) {
        bane.lock();
        try {
            kø.add(fly);
            ventefly.signalAll();
        }
        finally {
            bane.unlock();
        }
    }

    public Seilfly nesteSeilfly() {
        bane.lock();
        try {
            while (kø.size() == 0) {
                ventefly.await();
            }
            return kø.remove(0);
        }
        catch (InterruptedException e) {
            return null;
        }
        finally {
            bane.unlock();
        }
    }


}

public class Plassjef implements Runnable {
    KlarTilStart monitor;
    Konkurransegruppe konkurransegruppe;
    int antSlepe;

    public Plassjef(KlarTilStart mon, Konkurransegruppe konk, int ant) {
        this.monitor = mon;
        this.konkurransegruppe = konk;
        this.antSlepe = ant;
    }
    

    @Override
    public void run() {
        for (Seilfly fly: this.konkurransegruppe) {
            monitor.seilflyklar(fly);
        }
        for (int i = 0; i<antSlepe;i++) {
            SlepePilot slepe = new SlepePilot(this.monitor);
            Thread tråd = new Thread(slepe);
            tråd.start();
        }
    }
}

public class SlepePilot implements Runnable {
    KlarTilStart monitor;

    public SlepePilot(KlarTilStart monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(60*6*1000);
                monitor.nesteSeilfly();
            }
            catch (InterruptedException e) {
                return;
            }
        }
    }
}



