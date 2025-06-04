import java.util.Iterator;

public abstract class Seilfly {
    final private String Id;
    private int Glidetall;
    private int Vingespenn;

    public Seilfly(String Id, int Glidetall, int Vingespenn){
        this.Id = Id;
        this.Glidetall = Glidetall;
        this.Vingespenn = Vingespenn;
    }

    public String hentId(){
        return this.Id;
    }

    public int HentGlidetall(){
        return this.Glidetall;
    }

    public int HentVingespenn(){
        return this.Vingespenn;
    }    
}

public class EkteSeilfly extends Seilfly{
    public EkteSeilfly(String Id, int Glidetall, int Vingespenn){
        super(Id, Glidetall, Vingespenn);
    }
}

public interface Motordrevet {
    public int trekkraft();
    public String motortype();    
}

public abstract class MotorSeilfly extends Seilfly {
    private int trekkraftV;
    private String motortypeV;

    public MotorSeilfly(String Id, int Glidetall, int Vingespenn, int trekkraftV, String motortypeV){
        super(Id, Glidetall, Vingespenn);
        this.trekkraftV = trekkraftV;
        this.motortypeV = motortypeV;
    }

    public int trekkraft(){
        return this.trekkraftV;
    }

    public String motortype(){
        return this.motortypeV;
    }
}
public class TMG extends MotorSeilfly {
    public TMG(String Id, int Glidetall, int Vingespenn, int trekkraftV, String motortypeV) {
        super(Id, Glidetall, Vingespenn, trekkraftV, motortypeV);
    }
}

public class SLG extends MotorSeilfly {
    public SLG(String Id, int Glidetall, int Vingespenn, int trekkraftV, String motortypeV) {
        super(Id, Glidetall, Vingespenn, trekkraftV, motortypeV);
    }
}

public class SSG extends MotorSeilfly {
    public SSG(String Id, int Glidetall, int Vingespenn, int trekkraftV, String motortypeV){
        super(Id, Glidetall, Vingespenn, trekkraftV, motortypeV);
    }
}


public class Konkurransegruppe implements Iterable<Seilfly>{
    Node first;

    public void leggTil(Seilfly fly) {
        Node newNode = new Node(fly);
        if (this.first == null) {
            first = newNode;
        }
        else {
            Node current = this.first;
            while (current.next != null) {
                current = current.next;
            }
            newNode.previous = current;
            current.next = newNode;
        }
    }

    public boolean erMed(String id) {
        Node pointer = first;
        while (pointer != null){
            if (pointer.getContent().hentId().equals(id)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public Seilfly taUt(String id) {
        Node pointer = first;
        if (first.getContent().hentId().equals(id)) {
            Seilfly removed = pointer.getContent();
            first.next.setPrevious(null);
            first = first.next;
            return removed;
        }
        pointer = pointer.next;
        while (pointer != null) {
            if (pointer.getContent().hentId().equals(id)) {
                Seilfly removed = pointer.getContent();
                pointer.next.setPrevious(pointer.previous);
                pointer.previous.setNext(pointer.next);
                return removed;
            }
        }
        return null;
    }

    public SeilflyIterator iterator() {
        return new SeilflyIterator();
    }

    public Seilfly[] hentEkteSeilfly() {
        int antEkte = 0;
        for (Seilfly fly : this) {
            if (fly instanceof EkteSeilfly) {
                antEkte++;
            }
        }
        Seilfly[] ekteSeilFly = new Seilfly[antEkte];
        int counter = 0;
        for (Seilfly fly : this) {
            if (fly instanceof EkteSeilfly) {
                ekteSeilFly[counter++] = (EkteSeilfly)fly;
            }
        }
    }

    public int besteGlidetall () {
        int besteGlidetall = 0;
        for (Seilfly fly : this) {
            if (fly.HentGlidetall() > besteGlidetall) {
                besteGlidetall = fly.HentGlidetall();
            }
        }
        return besteGlidetall;
    }

    public int størstSpenn() {
        return størstSpennR(first);
    }

    public int størstSpennR(Node node) {
        if (node == null) {
            return 0;
        }
        int spenn = størstSpennR(node.next);
        if (spenn > node.getContent().HentVingespenn()) {
            return spenn;
        }
        return node.getContent().HentVingespenn();
    }

    public int[] histogram() {
        int[] outputHist = new int[99+1];
        for (Seilfly fly : this) {
            int spenn = fly.HentGlidetall();
            if (spenn > 10 && spenn <= 99) {
                outputHist[fly.HentVingespenn()] = outputHist[fly.HentVingespenn()] + 1;
            }
        }
        return outputHist;
    }

    private class SeilflyIterator implements Iterator<Seilfly>{
        Node pointer = first;

        @Override
        public boolean hasNext() {
            return (pointer.next != null);
        }
        @Override
        public Seilfly next() {
            Seilfly seilfly = pointer.getContent();
            pointer = pointer.next;
            return seilfly;
        }
    }

    protected class Node {
        Node next;
        Node previous;
        private Seilfly content;

        public Node(Seilfly content) {
            this.content = content;
        }
        public void setNext(Node next) {
            this.next = next;
        }
        public void setPrevious(Node previous) {
            this.previous = previous;
        }
        public Seilfly getContent() {
            return this.content;
        }
    }
}

