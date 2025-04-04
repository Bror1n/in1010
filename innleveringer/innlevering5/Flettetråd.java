import java.util.concurrent.CountDownLatch;

public class Flettetråd implements Runnable{
    Monitor monitor;
    CountDownLatch latch;

    public Flettetråd(Monitor monitor, CountDownLatch latch){
        this.monitor = monitor;
        this.latch = latch;
    }

    @Override
    public void run(){

        while (true){   // jeg hadde tidligere while antall > 1 men siden jeg har nå en exit logic basert på 
                        // om tråden er ferdig eller ikke venter jeg nå på break statementet
                        // grunnen til at jeg ikke kan basere en while løkke med samme statementet stammer fra at det ikke er predefinert
            Frekvenstabell[] frekvenser = this.monitor.taUtTo(this.latch);
            if (frekvenser == null){
                break;
            }
            Frekvenstabell frekvenstabell = Frekvenstabell.flett(frekvenser[0], frekvenser[1]);
            this.monitor.settInn(frekvenstabell);
        }
        latch.countDown();
    }
}
