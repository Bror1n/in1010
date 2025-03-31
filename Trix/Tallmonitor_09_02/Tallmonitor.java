public class Tallmonitor {
    int detMinste;
    int detStørste;

    public Tallmonitor(int minst,int størst){
        this.detMinste = minst;
        this.detStørste = størst;
    }

    public boolean settMinste(int tall){
        this.detMinste = tall;
        return invariant();
    }
    
    public boolean settStørste(int tall){
        this.detStørste = tall;
        return invariant();
    }

    public boolean invariant(){
        return detMinste < detStørste;
    }
}