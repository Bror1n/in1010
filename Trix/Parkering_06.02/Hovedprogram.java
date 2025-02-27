public class Hovedprogram{
    public static void main(String[] args) {
        Parkeringsplass<Bil> parkeringsplass1 = new Parkeringsplass<>();
        Parkeringsplass<Motorsykkel> parkeringsplass2 = new Parkeringsplass<>();
        Motorsykkel motorsykkel = new Motorsykkel("fisk", 2);
        Bil bil = new Bil("apekatt", 0);

        parkeringsplass1.parker(bil);
        parkeringsplass2.parker(motorsykkel);

        parkeringsplass1.kjøreUt();
        parkeringsplass2.kjøreUt();

        parkeringsplass1.parker(motorsykkel);



    }    
    
    
}