import java.util.*;

public class Barberia implements Runnable {
    private Queue<Client> salaEspera;
    private int maxCadires;
    public Object condBarber;
    private static Barberia instance;

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
        this.condBarber = new Object();
        instance = this;
    }

    public synchronized Client seguentClient() {
        return salaEspera.poll();
    }

    public synchronized void entrarClient(Client c) {
        if (salaEspera.size() < maxCadires) {
            salaEspera.add(c);
            System.out.println("Client " + c.getNom() + " en espera");
            synchronized (condBarber) {
                condBarber.notify();
            }
        } else {
            System.out.println("No queden cadires, client " + c.getNom() + " se'n va");
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Client c = new Client(i);
            entrarClient(c);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 11; i <= 20; i++) {
            Client c = new Client(i);
            entrarClient(c);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe", barberia);
        barber.start();
        new Thread(barberia).start();
    }
}