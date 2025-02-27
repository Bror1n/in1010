public class staticExp {
    String apekatt;
    staticExp(String elg){
        this.apekatt = elg;
    }
    
    public static void printErlend() {
        System.out.println("Erlend");
    }

    public void hentApekatt() {
        System.out.println(this.apekatt);
    }
    public static void main(String[] args) {
        staticExp.printErlend();
        staticExp emp = new staticExp("Veronika");
        emp.hentApekatt();
        staticExp.printErlend();
        emp.printErlend();
    }
}
