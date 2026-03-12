import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int numFumades;
    private Random random;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.numFumades = 0;
        this.random = new Random();
    }

    public void fuma() {
        if (tabac != null && paper != null && llumi != null) {
            tabac = null;
            paper = null;
            llumi = null;
            System.out.println("Fumador " + id + " fumant");
            try {
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
                return;
            }
            numFumades++;
            System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
        }
    }
    
    public void compraTabac() {
        System.out.println("Fumador " + id + " comprant Tabac");
        tabac = estanc.venTabac();
    }

    public void compraPaper() {
        System.out.println("Fumador " + id + " comprant Paper");
        paper = estanc.venPaper();
    }

    public void compraLlumi() {
        System.out.println("Fumador " + id + " comprant Llumi ");
        llumi = estanc.venLlumi();
    }

    @Override
    public void run() {
        while (numFumades < 3) {
            compraTabac();
            if (tabac == null) break;
            compraPaper();
            if (paper == null) break;
            compraLlumi();
            if (llumi == null) break;

            fuma();
        }
    }
}
