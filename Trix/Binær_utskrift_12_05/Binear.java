public class Binear {
    static void skrivUtBinear(int n){
        rekHelp(n, 1);
    }
    
    static String rekHelp(int n, int l){
        if (n < l){
            return "";
        }
        if (n % l == 1 ){
            return "0" + rekHelp(n, l*2);
        }
        if (n % l == 0){
            return "1" + rekHelp(n, l*2);
        }
    }

    static void skrivUtBinear(int n){
        if (n == 0){
            System.out.println(0);
        }
        else {
            if (n > 1){
                skrivUtBinear(n/2);
            }
            System.out.println(n%2);
        }
    }
}
