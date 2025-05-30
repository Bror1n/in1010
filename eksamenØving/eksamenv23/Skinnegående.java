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

public class Tog {
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
                if (peker.neste == null && peker.forrige == null) {
                    this.første = this.siste = null;
                }
                if (peker.neste == null) {
                    siste = peker.forrige;
                    peker.forrige.neste = null;
                }
                if (peker.forrige == null) {
                    første = peker.neste;
                    peker.neste.forrige = null;
                }
                else {
                    peker.forrige.neste = peker.neste;
                    peker.neste.forrige = peker.forrige;
                }
                peker.neste = peker.forrige = null;
                return peker;
            }
        }
        return null;
    }

}

