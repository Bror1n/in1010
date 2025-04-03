public class Nedover implements Runnable{
    int value;
    Tallmonitor tallmonitor;
    
    public Nedover(Tallmonitor tallmonitor){
        this.tallmonitor = tallmonitor;
        value = Integer.MAX_VALUE;
    }

    @Override
    public void run(){
        while (this.tallmonitor.settStørste(value-1)){
            value--;
        }
        System.out.println("Nedover stopped at value " + Integer.toString(value-1));
    }
}
