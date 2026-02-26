import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private String nom;
    private Random random;
    private int numComensal;

    public Filosof(String nom, Forquilla esquerra, Forquilla dreta, int numComensal) {
        this.nom = nom;
        this.gana = 0;
        this.random = new Random();
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.numComensal = numComensal;
    }

    private void pensar() {
        System.out.println("Filosof: " + nom + " pensant");
        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void menjar() {
        System.out.println("Filosof: " + nom + " menja");
        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filosof: " + nom + " ha acabat de menjar");
    }

    public void agafarForquillaEsquerra() {

        synchronized (forquillaEsquerra) {
            while (forquillaEsquerra.getPropietari() != Forquilla.LLIURE) {
                try {
                    forquillaEsquerra.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Retenim la forquilla
            forquillaEsquerra.setPropietari(numComensal);
            System.out.println("Filosof " + numComensal + " agafa forquilla esquerra " + forquillaEsquerra.getNumero());
        }
    }

    public boolean agafarForquillaDreta() {
        synchronized (forquillaDreta) {
            if (forquillaDreta.getPropietari() != Forquilla.LLIURE) {
                return false;
            }

            while (forquillaDreta.getPropietari() != Forquilla.LLIURE) {
                try {
                    forquillaDreta.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            forquillaDreta.setPropietari(numComensal);
            System.out.println("Filosof " + numComensal + " agafa forquilla dreta " + forquillaDreta.getNumero());
            return true;
        }
    }

    public boolean agafarForquilles() {
        agafarForquillaEsquerra();

        if (!agafarForquillaDreta()) {
            deixarForquilles();
            gana++;
            System.out.println("Filosof " + numComensal + " gana = " + gana);
            esperarFallo();
            return false;
        }
        return true;
    }

    public void deixarForquilles() {
        boolean algunaMollada = false;

        synchronized (forquillaDreta) {
            if (forquillaDreta.getPropietari() == numComensal) {
                forquillaDreta.setPropietari(Forquilla.LLIURE);
                forquillaDreta.notifyAll();
                algunaMollada = true;
            }
        }
        synchronized (forquillaEsquerra) {
            if (forquillaEsquerra.getPropietari() == numComensal) {
                forquillaEsquerra.setPropietari(Forquilla.LLIURE);
                forquillaEsquerra.notifyAll();
                algunaMollada = true;
            }
        }

        if (algunaMollada) {
            System.out.println("Filosof " + numComensal + " deixa les forquilles");
        }
    }

    private void esperarFallo() {
        try {
            Thread.sleep(500 + random.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            boolean teForquilles = false;
            while (!teForquilles) {
                teForquilles = agafarForquilles();
            }
            menjar();
            deixarForquilles();
        }
    }
}
