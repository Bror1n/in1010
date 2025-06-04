import java.util.Iterator;

abstract class Fly {
    final String id;
    int MotorAntall;
    int MTOW;
    
    public Fly(String id, int MotorAntall, int MTOW){
        this.id = id;
        this.MotorAntall = MotorAntall;
        this.MTOW = MTOW;
    }
    public String hentid(){
        return this.id;
    }
    public int hentMotorAntall(){
        return this.MotorAntall;
    }
    public int hentMTOW(){
        return this.MTOW;
    }
}

abstract class Motorfly extends Fly implements Motordrevet{
    int trekkraftVal;
    public Motorfly(String id, int MotorAntall,int MTOW,int trekkraft){
        super(id, MotorAntall, MTOW);
        this.trekkraftVal = trekkraft;
    }
    public int trekkraft(){
        return this.trekkraftVal;
    }
}

public class Lastefly extends Motorfly {
    int lastevekt;
    public Lastefly(String id, int MotorAntall, int MTOW, int trekkraft, int lastevekt){
        super(id, MotorAntall, MTOW, trekkraft);
        this.lastevekt = lastevekt;
    }

} 

public class Passasjerfly extends Motorfly {
    int passasjerplass;
    public Passasjerfly(String id, int MotorAntall, int MTOW, int trekkraft, int passasjerplass){
        super(id, MotorAntall, MTOW, trekkraft);
        this.passasjerplass = passasjerplass;
    }

}

public class Seilfly extends Fly {
    int minSynke;
    public Seilfly(String id, int MotorAntall,int MTOW,int minSynke){
        super(id, MotorAntall, MTOW);
        this.minSynke = minSynke;
    }

}


public interface Motordrevet {
    public int trekkraft();
}

public class Flyformasjon implements Iterable<Fly> {
    Node first;

    public void leggTil(Fly fly){
        Node newNode = new Node(fly);
        newNode.setNext(this.first);
        this.first = newNode;
    }

    public boolean erMed(String id){
        Node peker = this.first;
        while (peker != null){
            if (peker.getfly().hentid().equals(id)){
                return true;
            }
            peker = peker.next;
        }
        return false;
    }

    public Fly taUt(String id){ // Denne metoden antar at vært fly er unikt
        Node peker = this.first;

        if (peker != null && peker.getfly().hentid().equals(id)){
            Fly deleted = this.first.getfly();
            first = first.next;
            return deleted;
        }

        while (peker != null){
            if (peker.next.getfly().hentid().equals(id)){
                Fly deleted = peker.next.getfly();
                peker.setNext(peker.next.next);
                return deleted;
            }
            peker = peker.next;
        }
        return null;
    }

    public Iterator<Fly> Iterator() {
        return new FlyIterator();
    }

    class FlyIterator implements Iterator<Fly> {
        Node peker = first;

        @Override
        public boolean hasNext() {
            return peker != null;
        }

        @Override 
        public Fly next(){
            Node placeholder = peker;
            peker = peker.next;
            return placeholder.getfly();
        }
    }

    public Passasjerfly[] hentPassasjerFly(){
        Node peker = this.first;
        int lengde = 0;
        while (peker != null){
            if (peker.getfly() instanceof Passasjerfly){
                lengde +=1;
            }
            peker = peker.next;
        }

        Passasjerfly[] passasjerfly = new Passasjerfly[lengde];
        int counter = 0;
        for (Fly fly: this){
            if (fly instanceof Passasjerfly){
                passasjerfly[counter] = (Passasjerfly)fly;
                counter += 1;
            }
        }
        return passasjerfly;
    }

    public int totalVekt(){
        int vekt = 0;
        for (Fly fly: this){
            vekt += fly.MTOW;
        }
    }

    public int maksVekt(){
        return maksVektRek(this.first);
    }

    private int maksVektRek(Node current){
        if (current == null){
            return 0;
        }
        int last = maksVektRek(current.next);
        if (last > current.getfly().hentMTOW()){
            return last;
        }
        return current.getfly().hentMTOW();
    }





    protected class Node{
        private Fly fly;
        protected Node next;

        public Node(Fly fly){
            this.fly = fly;
        }

        public void setNext(Node node){
            this.next = node;
        }

        public Fly getfly(){
            return this.fly;
        }

    }
} 

//
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.Condition;



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