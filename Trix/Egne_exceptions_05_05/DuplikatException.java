public class DuplikatException extends Exception{
    public DuplikatException(String id) {
        super("Boken " + id + " Er et duplikat");
    }
}
