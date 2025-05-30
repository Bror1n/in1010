import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Konkurransegruppe {

}


public class Plassjef implements Runnable {
    int antFly;
    Konkurransegruppe gruppe;

    public Plasssjef(int antFly, Konkurransegruppe gruppe){
        this.antFly = antFly;
        this.gruppe = gruppe;
        for (int i=0;i<antFly;i++){
            Fly fly = new SlepeFly();
        }
        for (Fly fly: gruppe){

        }

    }
    @Override
    public void run() {

    }
}

public class SlepePilot implements Runnable {

    @Override
    public void run() {

    }
}

public class KlarTilStart {
    ReentrantLock lock = new ReentrantLock();
    Condition flyVenter = queue.newCondition();



}
