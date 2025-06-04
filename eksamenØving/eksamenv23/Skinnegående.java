import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

abstract public class Skinnegående {
    private final String id;
    private int sporvidde;
    public Skinnegående neste;
    public Skinnegående forrige;

    public Skinnegående(String id, int sporvidde) {
        this.id = id;
        this.sporvidde = sporvidde;
    }
    public String hentId() {
        return this.id;
    }
    public int hentSporvidde() {
        return this.sporvidde;
    }
}

public interface Motordrevet {
    public boolean fossilt();
    public int trekkraft();
}

public class Lokomotiv extends Skinnegående implements Motordrevet {
    private boolean foosiltV;
    private int trekkraftV;

    public Lokomotiv(String id, int sporvidde,boolean foosiltV, int trekkraftV) {
        super(id,sporvidde);
        this.foosiltV = foosiltV;
        this.trekkraftV = trekkraftV;
    }
    @Override
    public boolean fossilt() {
        return this.foosiltV;
    }
    @Override
    public int trekkraft() {
        return this.trekkraftV;
    }
}

public abstract class Vogn extends Skinnegående {
    private int lengde;

    public Vogn(String id, int sporvidde,int lengde) {
        super(id, sporvidde);
        this.lengde = lengde;
    }
}

public class Godsvogn extends Vogn {
    private double lastevekt;

    public Godsvogn(String id, int sporvidde, int lengde, double lastevekt) {
        super(id,sporvidde,lengde);
        this.lastevekt = lastevekt;
    }
}

public class Passasjervogn extends Vogn {
    private int plasser;

    public Passasjervogn(String id, int sporvidde, int lengde, int plasser) {
        super(id,sporvidde,lengde);
        this.plasser = plasser;
    }
}

public class Tog implements Iterable<Skinnegående>{
    Skinnegående første,siste;
    
    public void leggTil(Skinnegående skinnegående) {
        if (første == null) {
            første = skinnegående;
            siste = skinnegående;
        }
        else {
            siste.neste = skinnegående;
            skinnegående.forrige = siste;
            siste = skinnegående;
        }
    }

    public Skinnegående taUt(Skinnegående skinnegående) {
        if (skinnegående.forrige == null && skinnegående.neste == null) {
            første = null;
            siste = null;
        }
        else if (skinnegående.forrige == null) {
            første = skinnegående.neste;
            skinnegående.neste.forrige = null;
        }
        else if (skinnegående.neste == null) {
            siste = skinnegående.forrige;
            skinnegående.forrige.neste = null;
        }
        else {
            skinnegående.forrige.neste = skinnegående.neste;
            skinnegående.neste.forrige = skinnegående.forrige;
        }
        skinnegående.forrige = skinnegående.neste = null;
        return skinnegående;
    }

    public Skinnegående finnOgTaUt(String id) {
        Skinnegående peker = første;
        while (peker != null) {
            if (peker.hentId().equals(id)) {
                return taUt(peker);
            }
            peker = peker.neste;
        }
        return null;
    }

    public void leggTilForan(Skinnegående iTog, Skinnegående ny) {
        ny.neste = iTog;
        ny.forrige = iTog.forrige;
        if (iTog.forrige == null) {
            this.første = ny;
        }
        else {
            iTog.forrige.neste = ny;
        }
        iTog.forrige = ny;
    }
    public Iterator<Skinnegående> iterator() {
        return new TogIterator();
    }

    private class TogIterator implements Iterator<Skinnegående> {
        Skinnegående peker = første;


        @Override
        public boolean hasNext(){
            return peker != null;
        }

        @Override 
        public Skinnegående next(){
            Skinnegående res = peker;
            peker = peker.neste;
            return res;
        }
    }

    public Passasjervogn[] hentPassasjervogner() {
        int teller = 0;
        for (Skinnegående s: this) {
            if (s instanceof Passasjervogn) {
                teller += 1;
            }
        }
        Passasjervogn[] passasjervogn = new Passasjervogn[teller];
        int i = 0;
        for (Skinnegående s:this) {
            if (s instanceof Passasjervogn) {
                passasjervogn[i] = (Passasjervogn)s;
                i += 1;
            }
        }
        return passasjervogn;
    }

    public void sjekkSporvidde() throws FeilSporvidde{
        if (første == null) {
            return;
        }
        int sporvidde = første.hentSporvidde();
        for (Skinnegående s: this) {
            if (s.hentSporvidde() != sporvidde) {
                throw new FeilSporvidde();
            }
        }

    }

    public void leggTilSikker(Skinnegående skinnegående) throws FeilSporvidde {
        if (første == null) {
            første = skinnegående;
            siste = skinnegående;
        }
        else {
            if (siste.hentSporvidde() != skinnegående.hentSporvidde()){
                throw new FeilSporvidde();
            }
            else {
                siste.neste = skinnegående;
                skinnegående.forrige = siste;
                siste = skinnegående;  
            }
        } 
    }

    public void sjekkSporviddeR() throws FeilSporvidde {
        if (første == null){
            return;
        }
        this.hjelpeR(første,første.hentSporvidde());
    }

    public void hjelpeR(Skinnegående skinnegående,int bredde) throws FeilSporvidde{
        if (skinnegående == null){
            return;
        }
        if (bredde != skinnegående.hentSporvidde()){
            throw new FeilSporvidde();
        }
        hjelpeR(skinnegående.neste, bredde);
    }
}

public class FeilSporvidde extends Exception {
    public FeilSporvidde() {
        super("Toget har vogner med forskjellige sporvidder!");
    }
}

public class Monitor {
    ArrayList<Skinnegående> passer;
    ReentrantLock laas = new ReentrantLock();
    Condition ferdigleting = laas.newCondition();




    public void leggTil(Skinnegående skinnegående) {
        laas.lock();
        try {
            this.passer.add(skinnegående);
        }
        finally {
            laas.unlock();
        }
    }

    public void ferdigLeting() {
        laas.lock();
        try {
            ferdigleting.signalAll();
        }
        finally {
            laas.unlock();
        }
    }

    public Skinnegående hentNeste() throws InterruptedException{
        laas.lock();
        try {
            while (passer.size() == 0) {
                ferdigleting.await();
                return passer.remove(0); 
            }
        }

        finally {
            laas.unlock();
        }
    }

}

public class Resultat implements Runnable {
    Monitor monitor;

    public Resultat(Monitor monitor) {
        this.monitor = monitor;
    }




    @Override
    public void run() {
        while (true) {
            try {
                Skinnegående skinnegående = monitor.hentNeste();
                System.out.println(skinnegående.hentId());
            }
            catch (InterruptedException e) {
                System.exit(1);
            }
        }
    }
}

public class Leter implements Runnable {
    Monitor monitor;
    Tog tog;
    String string;

    public Leter(Monitor monitor, Tog tog, String string) {
        this.monitor = monitor;
        this.tog = tog;
        this.string = string;
    }


    @Override
    public void run() {
        for (Skinnegående s: this.tog) {
            if (s.hentId().startsWith(string)) {
                monitor.leggTil(s);
            }
        }
        monitor.ferdigLeting();
    }
}
