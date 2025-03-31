public class Katt implements Comparable<Katt>{
    int alder;
    String navn;

    public Katt(int alder, String navn) {
        this.alder = alder;
        this.navn = navn;
    }
    
    @Override
    public String toString() {
        return String.valueOf(alder) + " " + navn;
    }
    
    @Override
    public int compareTo(Katt katt){
        if (katt.alder > this.alder) {
            return -1;
        }
        if (this.alder > katt.alder) {
            return 1;
        }
        return 0;
    }
}
