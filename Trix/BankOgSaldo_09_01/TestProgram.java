import java.util.Random;

public class TestProgram {
    public static void main(String[] args) {
        Bank banken = new Bank(10000);
        Random rand = new Random();
        for (int i = 0;i<25;i++){
            TaPenger taPenger = new TaPenger(banken,rand.nextInt(4001)+1000);
            Thread tråd = new Thread(taPenger);
            System.out.println(tråd + " "+ i + " starter");
            tråd.start();
        }
    }
}