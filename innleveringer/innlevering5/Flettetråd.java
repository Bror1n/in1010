public class Flettetråd implements Runnable{
    Monitor monitor;

    public Flettetråd(Monitor monitor){
        this.monitor = new Monitor();
    }

    @Override
    public void run(){
        while (monitor.antall() > 1){
            Frekvenstabell[] frekvenser = this.monitor.taUtTo();
            Frekvenstabell frekvenstabell = Frekvenstabell.flett(frekvenser[0], frekvenser[1]);
            this.monitor.settInn(frekvenstabell);
        }
    }
}
