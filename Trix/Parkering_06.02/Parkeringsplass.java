public class Parkeringsplass<E> {
    private E peker;
    public E parker(E kjøretøy) {
        this.peker = kjøretøy;
        return kjøretøy;
    }
    public E kjøreUt() {
        E mellomPeker = peker;
        this.peker = null;
        return mellomPeker;
    }
}
