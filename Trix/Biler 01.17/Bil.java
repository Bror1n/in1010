//a
public class Bil {
    private String eier;
    private String merke;
    public Bil(String eier, String merke) {
        this.eier = eier;
        this.merke = merke;
    }
    public String hentEier() {
        return this.eier;
    }
    public String hentMerke() {
        return this.merke;
    }
}
