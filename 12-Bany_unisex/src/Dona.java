import java.util.Random;

public class Dona extends Thread {
    private String nom;
    private BanyUnisex bany;
    private Random random;

    public Dona(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            bany.entraDona(nom);
            int temps = 2000 + random.nextInt(1001);
            bany.utilitzaLavabo(temps);
            bany.surtDona(nom);
        }
    }
}
