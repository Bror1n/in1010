public class SkrivUtArrayRek {
    public static void main(String[] args) {
        String[] strenger = {"IN1010", "er", "verdens", "beste", "fag!"};
        SkrivUtArrayRek.rek(0, strenger);


    }
    public static void rek(int i, String[] strenger) {
        if (i == strenger.length){
            return;
        }
        System.out.println(strenger[i] + " ");
        rek(i++,strenger);
    }
}
