class FlaskeBruk {
    public static void main (String[] a){
        Drikkeflaske drikke = new Drikkeflaske();
        Plastflaske plast = new Plastflaske();
        Miljøplastflaske miljø = new Miljøplastflaske();
        Pappflaske papp = new Pappflaske();
        Glassflaske flass = new Glassflaske();
        PappflaskeMedPant pappPant = new PappflaskeMedPant();


        Flaske f1 = pappPant;
        Plastflaske pl1 = miljø;

        Pant p1 = plast;
        Pant p2 = pappPant;
        Pant p3 = miljø;
        Pant p4 = (Pant)f1;
        Pant p5 = (Pant)pl1;
        // Pant p6 = f1;
        Pant p7 = pl1;
        Papirsøppel ps1 = papp;
        Papirsøppel ps2 = pappPant;
        Papirsøppel ps3 = (Papirsøppel)f1;
        //Papirsøppel ps4 = f1;
        Flaske f2 = drikke;
        Flaske f3 = pl1;
        Plastflaske pl2 = miljø;
        // Pappflaske pf1 = f1;
        
    }
}