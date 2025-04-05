import java.util.HashSet;
import java.util.TreeMap;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;


class Frekvenstabell extends TreeMap<String, Integer> {
    @Override
    public String toString() {
        String string = "";
        for (String key : this.keySet()){
            string += key + " " + Integer.toString(this.get(key)) + "\n";
        }
        return string;
    }


    public static Frekvenstabell flett(Frekvenstabell f1, Frekvenstabell f2) {
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(f1.keySet());
        allKeys.addAll(f2.keySet());

        for (String key : allKeys){
            if (!f1.containsKey(key)){
                f1.put(key,f2.get(key));
            }
            else if (f1.containsKey(key) && f2.containsKey(key)){
                f1.put(key,f1.get(key)+f2.get(key));
            }
        }
        return f1;
    }

    public void skrivTilFil(String filnavn) {
        try (FileWriter writer = new FileWriter(filnavn)){
            for (String key : this.keySet()){
                writer.append(key+","+Integer.toString(this.get(key))+"\n");
            }
        }
        catch(IOException e) {
            e.printStackTrace(); //chatgpt
        }
    }
}