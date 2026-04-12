import java.util.Random;

public class Home extends Thread {
    private String nom;
    private BanyUnisex bany;
    private Random random;

    public Home(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            bany.entraHome(nom);
            int temps = 1000 + random.nextInt(1001);
            bany.utilitzaLavabo(temps);
            bany.surtHome(nom);
        }
    }
}
