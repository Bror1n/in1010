public class Butikk {
    private Person nesteMann;

    public Butikk(Person førstePerson) {
        this.nesteMann = førstePerson;
    }

    public void entreButikk(Person person) {
        Person peker = nesteMann;
        while (peker.hentNeste() != null){
            peker = peker.hentNeste();
        }
        peker.settNeste(person);
    }

    public void kassa(){
        Person peker = nesteMann;
        while (peker.hentNeste() != null) {
            System.out.print(peker.hentNavn());
            System.out.print("kjøper");
            System.out.println(peker.hentGjenstand());
            System.out.println("- Hade bra!");
            peker = peker.hentNeste();
        }
    }

    
}
 