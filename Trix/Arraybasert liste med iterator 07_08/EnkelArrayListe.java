import java.util.Iterator;

public class EnkelArrayListe implements Iterable<String>{
    String[] strings; 
    int teller = 0;
    int lengde = 0;
    
    public EnkelArrayListe(int størrelse){
        strings = new String[størrelse];
        this.lengde = størrelse;
    }

    public void settInn(String string){
        if (this.teller >= this.lengde) {
            String[] nyeStrings = new String[lengde*2];
            for (int i = 0;i<this.lengde;i++){
                nyeStrings[i] = this.strings[i];
            }
            this.lengde = this.lengde *2;
            this.strings = nyeStrings;
        }
        this.strings[this.teller] = string;
        this.teller++;
    }
    public Iterator<String> iterator(){
        return new ListeIterator();
    }

    private class ListeIterator implements Iterator<String>{
        private int gjeldendeIndeks = 0;

        public boolean hasNext(){
            return gjeldendeIndeks < lengde;
        }
        public String next(){
            return strings[gjeldendeIndeks++];
        }
    }
}