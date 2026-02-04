import java.util.Random;

public class Assistent extends Thread {
    private Esdeveniment esdeveniment;
    private Random random;

    public Assistent(String name, Esdeveniment esdeveniment) {
        super(name);
        this.esdeveniment = esdeveniment;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            if (random.nextBoolean() == true) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelarReserva(this);
            }
            try {
                // sleep aleatori entre 0 i 1000ms
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(getName() + " interromput.");
                break; // Sortir del bucle si s'ha interromput
            }
        }
    }
}
