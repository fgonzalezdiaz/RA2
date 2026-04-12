import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {

    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    public static final int CAPACITAT_MAX = 3;

    private int estatActual;
    private int ocupants;

    private Semaphore capacitat;
    private ReentrantLock lockEstat;

    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();

        for (int i = 1; i <= 5; i++) {
            new Home("Home-" + i, bany).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Dona("Dona-" + i, bany).start();
        }
    }

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }

    public void entraHome(String nom) {
        boolean dins = false;
        while (!dins) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_HOMES;
                        System.out.println(nom + " (Home) entra al bany. Ocupants: " + ocupants);
                        dins = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            if (!dins) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void entraDona(String nom) {
        boolean dins = false;
        while (!dins) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_DONES;
                        System.out.println(nom + " (Dona) entra al bany. Ocupants: " + ocupants);
                        dins = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            if (!dins) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println(nom + " (Home) ha acabat d'usar el bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany esta buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println(nom + " (Dona) ha acabat d'usar el bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany esta buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void utilitzaLavabo(int milisegons) {
        try {
            Thread.sleep(milisegons);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
