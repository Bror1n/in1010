import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FraFil {
   public FraFil(String filnavn) throws FileNotFoundException {
        File fil = new File(filnavn);
        Scanner leser = new Scanner(fil);

        while (leser.hasNextLine()) {
            String line = leser.nextLine();
            System.out.print(line);;
        }

   }
}
