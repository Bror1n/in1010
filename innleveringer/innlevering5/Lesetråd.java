public  class Lesetråd implements Runnable{
    Monitor monitor;
    String filnavn;

    public Lesetråd(Monitor monitor,String filnavn) {
       this.monitor = monitor;
       this.filnavn = filnavn;
    }


    @Override
    public void run() {
        monitor.settInn(Monitor.les(this.filnavn));
    }
    
}
