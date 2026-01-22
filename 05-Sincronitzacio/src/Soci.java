import java.util.Random;

public class Soci extends Thread{
    private Compte compte;
    private int aportacio;
    private int esperaMax;
    private Random rnd; 
    private int maxAnys;


    public Soci() {
        this.aportacio = 10;
        this.esperaMax = 100;
        this.maxAnys = 10;
        this.rnd = new Random();
        this.compte = Compte.getInstance(); // Gracias a esto y al metodo creado getInstance() solo crea la instancia si no existe, de lo contrario devuelve la misma
    }
    public Compte getCompte(){
        return compte;
    }

    @Override
    public void run(){
        for(int i = 0; i < this.maxAnys*12; i++){
            synchronized(compte){
                if(i % 2 == 0){
                    compte.ingres(aportacio);
                } else {
                    compte.retira(aportacio);
                }
            }
            try {
                Thread.sleep(rnd.nextInt(esperaMax));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    
}
