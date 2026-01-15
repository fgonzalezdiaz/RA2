import java.util.Random;
public class DormAleatori extends Thread {
    private long momentDeCreacio;   
    private Random random = new Random();

    public DormAleatori(String nom) {
        super(nom);
        this.momentDeCreacio = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            long intervalAleatori = random.nextInt(1000);
            long tempsTotal = System.currentTimeMillis() - momentDeCreacio;
            System.out.println(getName() + "(" + i + ") a dormir " + intervalAleatori + "ms total " + tempsTotal + "ms");
            try {
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        System.out.println("-- Fi de main ---------");
        joan.start();
        pep.start();
    }
}