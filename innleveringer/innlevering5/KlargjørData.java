import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KlargjørData {
    public static void main(String[] args) {
        String filnavn = args[0];
        File fil = new File(filnavn);
        String mappe = fil.getParent() + "/";

        Monitor smittetMonitor = new Monitor();
        Monitor friskMonitor = new Monitor();

        try {
            Scanner scanner = new Scanner(new File(mappe+"metadata.csv"));
            while (scanner.hasNextLine()){
                String string = scanner.nextLine();
                String[] values = string.split(",");
                
                if (Boolean.valueOf(values[1])){
                    Lesetråd lesetråd = new Lesetråd(smittetMonitor, values[0]);
                    Thread trådlese = new Thread(lesetråd);
                    trådlese.start();
                }
                else {
                    Lesetråd lesetråd = new Lesetråd(friskMonitor, values[0]);
                    Thread trådlese = new Thread(lesetråd);
                    trådlese.start();
                } 
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }        
    } 
}
