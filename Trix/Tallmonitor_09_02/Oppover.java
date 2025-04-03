public class Oppover implements Runnable{
    int value;
    Tallmonitor tallmonitor;

    public Oppover(Tallmonitor tallmonitor){
        this.tallmonitor = tallmonitor;
        value = Integer.MIN_VALUE;
    }

    @Override
    public void run(){
        while (tallmonitor.settMinste(value+1)){
            value++;
        }
        System.out.println("Oppover stopped at value " + Integer.valueOf(value+1));
    }
    
}
