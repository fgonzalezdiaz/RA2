import java.util.Random;

public class Motor extends Thread {
    private int id;
    private int potActual;
    private int potObjectiu;
    private Random random;
    private boolean iniciat; 

    public Motor(int id) {
        this.id = id;
        this.potActual = 0;
        this.potObjectiu = 0;
        this.random = new Random();
        this.iniciat = false;
    }

    public void setPotencia(int p) {
        this.potObjectiu = p;
        if (p > 0 && p <= 10) {
            this.iniciat = true;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (this.iniciat && this.potObjectiu == 0 && this.potActual == 0) {
                break; 
            }

            if (!this.iniciat) {
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            String accio = "";
            if (this.potActual < this.potObjectiu) {
                this.potActual++;
                accio = "Incre.";
            } else if (this.potActual > this.potObjectiu) {
                this.potActual--;
                accio = "Decre.";
            } else {
                accio = "FerRes";
            }

            System.out.println("Motor " + id + ": " + accio + " Objectiu: " + potObjectiu + " Actual: " + potActual);
            try {
                Thread.sleep(random.nextInt(1001) + 1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}