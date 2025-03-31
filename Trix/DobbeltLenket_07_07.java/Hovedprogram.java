public class Hovedprogram {
    public static void main(String[] args) {
        DobbelLenke<String> liste = new DobbelLenke<>();
        liste.settInn("Ja");
        liste.settInn("vi");
        liste.settInn("elsker");
        liste.settInn("dette");
        liste.settInn("landet");

        liste.test();

    }
 
}
