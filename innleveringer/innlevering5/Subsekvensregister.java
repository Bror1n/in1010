import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Subsekvensregister {
    private ArrayList<Frekvenstabell> register;
    private static Integer Subsekvensregister = 3;

    public Subsekvensregister(){
        register = new ArrayList<Frekvenstabell>();
    }

    public void settInn(Frekvenstabell f){
        register.add(f);
    }

    public Frekvenstabell taUt(){
        return register.remove(register.size()-1);

    }
    
    public int antall(){
        return register.size();
    }

    public static Frekvenstabell les(String filnavn){
        Frekvenstabell f = new Frekvenstabell();
        try (Scanner scanner = new Scanner(new File(filnavn))) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                int j = 0;
                for (int i=Subsekvensregister;i<line.length()+1;i++){
                    f.put(line.substring(j, i),1);
                    j++;
                }
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return f;
    }

    public void makeFile(String filnavn){
        register.get(0).skrivTilFil(filnavn);
    }
}
