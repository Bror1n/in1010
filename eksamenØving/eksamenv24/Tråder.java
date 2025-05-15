import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;;;

public class Rullebane {

    int venter;
    ReentrantLock rullebane = new ReentrantLock();
    Condition avventStartTillatelse = rullebane.newCondition();

    public Rullebane(){
        this.venter = 0;
    }


    public void sjekkAvganger(){
        rullebane.lock();
        try {
            if (venter > 0){
                avventStartTillatelse.signalAll();
                venter--;
            }
        }
        finally {
            rullebane.unlock();
        }
    }

    public void hentStartTillatelse(Fly fly){
        rullebane.lock();
        try {
            venter++;
            avventStartTillatelse.await();
        }
        catch (InterruptedException e) {
            return;
        }
        finally {
            rullebane.unlock();
        }
    }
}

public class Flygeleder implements Runnable {
    Rullebane monitor;

    public Flygeleder(Rullebane monitor){
        this.monitor = monitor;
    }


    @Override
    public void run() {
        while (true){
            this.monitor.sjekkAvganger();
            try {
                Thread.sleep(60000);
            }
            catch (InterruptedException e ){return;}
        }
    }

}

public class Pilot implements Runnable {
    Rullebane monitor;
    Fly fly;

    public Pilot(Rullebane monitor,Fly fly) {
        this.monitor = monitor;
        this.fly = fly;
    }

    @Override
    public void run(){
        monitor.hentStartTillatelse(this.fly);
    }
}


//5d 

public class Rullebane2 {

    int venter;
    ReentrantLock rullebane = new ReentrantLock();
    Condition avventStartTillatelse = rullebane.newCondition();
    Condition avventfly = rullebane.newCondition();

    public Rullebane2(){
        this.venter = 0;
    }


    public void sjekkAvganger(){
        rullebane.lock();
        try {
            if (venter > 0){
                avventStartTillatelse.signalAll();
                venter--;
            }
            else {
                avventfly.await();
                avventStartTillatelse.signalAll();
                venter--;
            }
        }
        catch (InterruptedException e) {
            return;
        }
        finally {
            rullebane.unlock();
        }
    }

    public void hentStartTillatelse(Fly fly){
        rullebane.lock();
        try {
            venter++;
            avventfly.signalAll();
            avventStartTillatelse.await();
        }
        catch (InterruptedException e) {
            return;
        }
        finally {
            rullebane.unlock();
        }
    }
}