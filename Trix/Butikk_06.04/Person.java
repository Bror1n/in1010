public class Person {
    private String navn;
    private String gjenstand;
    private Person nesteKø;

    public Person(String navn, String gjenstand) {
        this.navn = navn;
        this.gjenstand = gjenstand;
    }
    public void settNeste(Person nesteMann) {
        this.nesteKø = nesteMann;
    }
    public Person hentNeste() {
        return this.nesteKø;
    }
    public String hentGjenstand() {
        return this.gjenstand;
    }
    public String hentNavn() {
        return this.navn;
    }
    
}
