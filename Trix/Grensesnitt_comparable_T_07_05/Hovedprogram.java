import java.util.ArrayList;

public class Hovedprogram {
    public static void main(String[] args) {
        ArrayList<Katt> liste = new ArrayList<Katt>();
        Katt katt1 =  new Katt(20, "Erlend");
        Katt katt2 = new Katt(4,"Lars");
        Katt katt3 = new Katt(8,"Katrine");
        Katt katt4 = new Katt(5, "Kristoffer");
        // Ser du på meg kode?

        Katt største = liste.get(liste.size());
        for (int i = 0;i < liste.size()-1;i++) {
            if (liste.get(i).compareTo(liste.get(i+1))>-1 && liste.get(i).compareTo(største) > -1){
               største = liste.get(i);
            }
        }
        System.out.println(største);

        // Syntes ikke den forrige var så fin, hvordan kan vi gjøre dette med mindre operasjoner?

        største = liste.get(0);
        for (int i = 1;i<liste.size();i++){
            if (liste.get(i-1).compareTo(liste.get(i)) > 0) {
                største = liste.get(i-1);
            }
        }
        if (liste.get(liste.size()).compareTo(liste.get(liste.size()-1))> 0){
            største = liste.get(liste.size());
        }
        
        System.out.println(største);
    }
    
}
