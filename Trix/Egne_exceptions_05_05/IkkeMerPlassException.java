public class IkkeMerPlassException extends Exception {
    public IkkeMerPlassException(String id) {
        super("Boken " + id + " har ikke plass");
    }
}