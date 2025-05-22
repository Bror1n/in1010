public class TestMetode {
    public static void main(String[] args) {
        EnkeltLenket<String> liste = new EnkeltLenket<String>();
        liste.leggTil("Apekatt");
        liste.leggTil("FjellElefant");
        liste.leggTil("Kevdog");
        liste.leggTil("ErlePerle");
        liste.leggTil("LarsPåMars");

        for (String string: liste){
            System.out.println(string);
        }
    }
}
