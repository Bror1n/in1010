public class testClass {
    public static void main(String[] args) {
        Bil bil1 = new Bil("Erlend","Porche");
        Bil bil2 = new Bil("Bror","wolkswagen");
        System.out.print("Erlend har en");
        System.out.println(bil1.hentEier());
        System.out.print("Bror har en");
        System.out.println(bil2.hentEier());
    }
}
