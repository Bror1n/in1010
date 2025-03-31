import java.util.HashMap;

public class hashTest {
    public static void main(String[] args) {
        HashMap<Integer,String> hashmap = new HashMap<>();
        hashmap.put(1, "apekatt");
        hashmap.put(2, "erlend");
        hashmap.put(3, "pære");
        hashmap.put(4, "iskrem");
        hashmap.put(5, "mullah");
       
        for (Integer key : hashmap.keySet()) {
            String value = hashmap.get(key);
            System.out.println("Integer: "+key+"String: "+value);
        }
    }
    
}
