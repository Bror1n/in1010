abstract public class Seilfly {
    String id;
    int glidetall;
    int vingespenn;

    public Seilfly(String id, int glidetall, int vingespenn){
        this.id = id;
        this.glidetall = glidetall;
        this.vingespenn = vingespenn;
    }

    public String hentid(){
        return this.id;
    }

    public int hentGlidetall(){
        return this.glidetall;
    }

    public int hentVingespenn(){
        return this.vingespenn;
    }
}
