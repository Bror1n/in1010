import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class KlargjørData {
    static int ANTALL_TRÅDER = 8;
    CountDownLatch smittetLatch;
    CountDownLatch friskLatch;


    public KlargjørData(){
        this.smittetLatch = new CountDownLatch(ANTALL_TRÅDER);
        this.friskLatch = new CountDownLatch(ANTALL_TRÅDER);
    }

    public void readData(Monitor smittetMonitor, Monitor friskMonitor, String mappe){
        try {
            Scanner scanner = new Scanner(new File(mappe+"metadata.csv"));
            ArrayList<Thread> trådliste = new ArrayList<Thread>();
            int i = 0;
            while (scanner.hasNextLine()){
                System.out.println("Starter lesetråd"+Integer.toString(i++));
                String string = scanner.nextLine();
                String[] values = string.split(",");
                
                if (Boolean.valueOf(values[1])){
                    Lesetråd lesetråd = new Lesetråd(smittetMonitor, mappe+values[0]);
                    Thread trådlese = new Thread(lesetråd);
                    trådlese.start(); 
                    trådliste.add(trådlese);
                }
                else {
                    Lesetråd lesetråd = new Lesetråd(friskMonitor, mappe+values[0]);
                    Thread trådlese = new Thread(lesetråd);
                    trådlese.start();
                    trådliste.add(trådlese);
                } 
            }
            scanner.close();
            for (Thread tråd : trådliste){
                tråd.join();
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }        
        catch (InterruptedException e){
            System.out.println("Trådet ble interupted");
        }   
    }

    public void flett(Monitor smitteMonitor, Monitor friskMonitor){
        for (int i = 0; i<KlargjørData.ANTALL_TRÅDER; i++){
            Flettetråd flettetråd = new Flettetråd(friskMonitor, this.friskLatch);
            Thread trådflette = new Thread(flettetråd);
            trådflette.start();
        }
        for (int i = 0; i<KlargjørData.ANTALL_TRÅDER; i++){
            Flettetråd flettetråd = new Flettetråd(smitteMonitor, this.smittetLatch);
            Thread trådflette = new Thread(flettetråd);
            trådflette.start();
        }
        try {
            this.smittetLatch.await();
            this.friskLatch.await();
        }
        catch (InterruptedException e){
            System.out.println("Thread was interupted while awaiting countdownlatch");
        }
    }

    public static void main(String[] args) {
        KlargjørData hoved = new KlargjørData();

        String filnavn = args[0];
        System.out.println("filnavn er " + filnavn);
        File fil = new File(filnavn);
        String mappe = fil.getParent() + "/";
        System.out.println("Mappen er "+mappe);

        Monitor smittetMonitor = new Monitor();
        Monitor friskMonitor = new Monitor();
        
        hoved.readData(smittetMonitor, friskMonitor, mappe);
        hoved.flett(smittetMonitor, friskMonitor);

        smittetMonitor.skrivTilFil("smittet.csv");
        friskMonitor.skrivTilFil("Frisk.csv");

    }
}
