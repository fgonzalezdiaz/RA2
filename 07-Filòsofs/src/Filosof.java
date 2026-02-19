import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private String nom;
    private Random random;

    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.gana =0;
        this.random = new Random();
        this.forquillaEsquerra =esquerra;
        this.forquillaDreta = dreta;
    }

    private void pensar() {
        System.out.println("Filosof: " + nom + " pensant");
        try {
            Thread.sleep(1000+ random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void menjar() {
        boolean haMenjat = false;
        while (!haMenjat) {
            boolean agafadaEsquerra= false;
            synchronized (forquillaEsquerra) {
                if (!forquillaEsquerra.isEnUs()) {
                    forquillaEsquerra.setEnUs(true);
                    agafadaEsquerra = true;
                    System.out.println("Filosof: " + nom+ " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
                }
            }

            if (agafadaEsquerra) {
                boolean agafadaDreta = false;
                synchronized (forquillaDreta) {
                    if (!forquillaDreta.isEnUs()) {
                        forquillaDreta.setEnUs(true);
                        agafadaDreta = true;
                        System.out.println("Filosof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                    }
                }

                if (agafadaDreta) {
                    System.out.println("Filosof: " +nom + " menja");
                    try {
                        Thread.sleep(1000 + random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Filosof: " + nom + " ha acabat de menjar");

                    synchronized (forquillaDreta) {
                        forquillaDreta.setEnUs(false);
                    }
                    synchronized (forquillaEsquerra) {
                        forquillaEsquerra.setEnUs(false);
                    }
                    haMenjat = true;
                } else {
                    System.out.println("Filosof: " +nom+ " deixa l'esquerra (" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                    synchronized (forquillaEsquerra) {
                        forquillaEsquerra.setEnUs(false);
                    }
                    gana++;
                    System.out.println("Filosof: " + nom + " gana = " +gana);
                    esperarFallo();
                }
            } else {
                esperarFallo();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }

    private void esperarFallo() {
        try {
            Thread.sleep(500 + random.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
