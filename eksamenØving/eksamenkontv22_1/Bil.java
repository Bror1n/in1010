import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;

public interface Elektrisk {
    public int elektrisk();
}

public abstract class Bil {
    final String bilnummer;
    protected int pris; 

    public Bil neste;
    public Bil forrige;

    public Bil(String bilnummer, int pris) {
        this.bilnummer = bilnummer;
        this.pris = pris;
    }

    public String toString() {
        return "id: " + this.bilnummer + " " + String.valueOf(pris); 
    }
}

public class Personbil extends Bil {
    private int antPas;

    public Personbil(String bilnummer, int pris, int antPas) {
        super(bilnummer, pris);
        this.antPas = antPas;
    }

    @Override
    public String toString() {
        return super.toString() + antPas;
    }
}

public class Varebil extends Bil {
    int lastevolum;

    public Varebil(String bilnummer, int pris, int lastevolum) {
        super(bilnummer, pris);
        this.lastevolum = lastevolum;
    }

    @Override
    public String toString() {
        return super.toString() + lastevolum;
    }


}

public class ElektriskPersonbil extends Personbil implements Elektrisk {
    private int batteri;

    public ElektriskPersonbil(String bilnummer, int pris, int antPas, int batteri) {
        super(bilnummer, pris, antPas);
        this.batteri = batteri;
    }

    @Override
    public String toString() {
        return super.toString() + batteri;
    }

    @Override
    public int elektrisk() {
        return batteri;
    }
}

public class ElektriskVarebil extends Varebil implements Elektrisk {
    private int batteri;
    
    public ElektriskVarebil(String bilnummer, int pris, int lastevolum, int batteri) {
        super(bilnummer, pris, lastevolum);
        this.batteri = batteri;
    }

    @Override
    public String toString() {
        return super.toString() + batteri;
    }

    @Override
    public int elektrisk() {
        return batteri;
    }
}

public interface Dialog {
    public boolean svarJaEllerNei(String sporsmal);
}

class TastaturDialog implements Dialog {
    Scanner tastatur = new Scanner(System.in);

    @Override
    public boolean svarJaEllerNei (String sporsmal) {
        while (true) {
            System.out.print(sporsmal + " ");
            String svar = tastatur.nextLine().trim().toLowerCase();
            if (svar.charAt(0) == 'j') return true;
            if (svar.charAt(0) == 'n') return false;
        }
    }
}


public class GUIDialog implements Dialog {
    JFrame vindu = null;
    JPanel panel;
    JLabel tekstfelt;
    JButton jaknapp, neiknapp;

    Thread hovedtrad = Thread.currentThread();
    boolean svaret = true;

    @Override
    public boolean svarJaEllerNei (String sporsmal) {
	if (vindu == null) {
	    try {
		UIManager.setLookAndFeel(
		    UIManager.getCrossPlatformLookAndFeelClassName());
	    } catch (Exception e) { System.exit(1); }
	    vindu = new JFrame("JA eller NEI?");
	    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    panel = new JPanel();
	    vindu.add(panel);

	    tekstfelt= new JLabel(sporsmal);
	    panel.add(tekstfelt);

	    class SvarJaNei implements ActionListener {
		boolean svar;
		
		SvarJaNei (boolean jn) {
		    svar = jn;
		}
		
		@Override
		public void actionPerformed (ActionEvent e) {
		    svaret = svar;
		    hovedtrad.interrupt();
		}
	    }

	    jaknapp = new JButton("JA");
	    jaknapp.addActionListener(new SvarJaNei(true));
	    panel.add(jaknapp);

	    neiknapp = new JButton("NEI");
	    neiknapp.addActionListener(new SvarJaNei(false));
	    panel.add(neiknapp);

	    vindu.pack();  vindu.setVisible(true);
	} else {
	    tekstfelt.setText(sporsmal);
	}

	try {
	    Thread.sleep(1000000);
	} catch (InterruptedException e) {}

	return svaret;
    }
}




