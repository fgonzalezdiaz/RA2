import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread {
    private ArrayList<Tabac> llistaTabac;
    private ArrayList<Paper> llistaPaper;
    private ArrayList<Llumi> llistaLlumi;
    private boolean obert;
    private Random random;

    public Estanc() {
        this.llistaTabac = new ArrayList<>();
        this.llistaPaper = new ArrayList<>();
        this.llistaLlumi = new ArrayList<>();
        this.obert = true;
        this.random = new Random();
    }

    public synchronized void addTabac() {
        llistaTabac.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addLlumi() {
        llistaLlumi.add(new Llumi());
        System.out.println("Afegint Llumi");
        notifyAll();
    }

    public synchronized void addPaper() {
        llistaPaper.add(new Paper());
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public void nouSubministrament() {
        int r = random.nextInt(3);
        if (r == 0) {
            addTabac();
        } else if (r == 1) {
            addPaper();
        } else {
            addLlumi();
        }
    }

    public synchronized Tabac venTabac() {
        while (llistaTabac.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        if (!llistaTabac.isEmpty()) {
            return llistaTabac.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() {
        while (llistaPaper.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        if (!llistaPaper.isEmpty()) {
            return llistaPaper.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() {
        while (llistaLlumi.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        if (!llistaLlumi.isEmpty()) {
            return llistaLlumi.remove(0);
        }
        return null;
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll();
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(1001));
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Estanc tancat");
    }
}
