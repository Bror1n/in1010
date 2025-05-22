public class RekrusivtUtskriftKlasse {
    public static void RekrusivtUtskrift(int i) {
        if (i >= 10) {
            return;
        }
        System.err.println(i);
        RekrusivtUtskrift(i++);

    } 
}
