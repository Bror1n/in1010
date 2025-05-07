
public class Run implements Runnable{
    int step;
    int end;
    Monitor monitor;

    public Run(int step, int end, Monitor monitor){
        this.end = end;
        this.step = step;
        this.monitor = monitor;
    }

    @Override 
    public void run() {
        for (int i = 0; i <= this.end; i++){
            if (i % this.step == 0){
                monitor.tall(i);
            }
        }
    }

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        for (int i = 0;i <= 10; i++){
            Runnable running = new Run(100,1000,monitor);
            Thread tråd = new Thread(running);
            tråd.start();
        }
    }
}
