class Bokhylle {
    private Bok[] boeker;

    public Bokhylle (int antPlasser) {
        boeker = new Bok[antPlasser];
    }

    //Forsoeker aa sette inn en bok paa foerste ledige plass i bokhyllen
    public void settInn(Bok b) throws DuplikatException, IkkeMerPlassException{

        for (int i = 0; i < boeker.length; i++) {
            if (boeker[i].equals(b.toString())) {
                throw new DuplikatException(b.toString());
            }
            if (boeker[i] == null) {

                boeker[i] = b;
                return;
            }
        }
        throw new IkkeMerPlassException(b.toString());
    }

    public void skrivBoeker() {
        for (Bok b: boeker) {
            if (b != null) {
                System.out.println(b);
            }
        }
    }
}