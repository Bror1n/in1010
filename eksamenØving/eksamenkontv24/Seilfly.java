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


public class Konkurransegruppe {
    
}

