public class Monitor {
    private Subsekvensregister subregister;
    
    public Monitor(){
        subregister = new Subsekvensregister();
    }

    public void settInn(Frekvenstabell f){
        subregister.settInn(f);
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
}
