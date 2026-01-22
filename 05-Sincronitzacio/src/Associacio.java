import java.util.ArrayList;

public class Associacio {
    private int numSocis;
    private Soci[] socis;


    public Associacio(){
        this.numSocis = 100;
        socis = new Soci[numSocis];
        for(int i = 0; i < numSocis; i++){
            Soci soci = new Soci();
            socis[i] = soci;
        }
    }
    
    public int getNumSocis() {
        return numSocis;
    }
    public void setNumSocis(int numSocis) {
        this.numSocis = numSocis;
    }



    public void iniciaCompteTempsSoci(){
        for(int i = 0; i < numSocis; i++){
            socis[i].start();
        }
    }
    
    public void esperaPeriodeSocis(){
        for(int i = 0; i < numSocis; i++){
            try{
                socis[i].join();
            } catch ( InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Tots han acabat ls seves operacions");
    }

    public void mostraBalancComptes() {
        float saldoFinal = Compte.getInstance().getSaldo();
        System.out.println("--- Resultats ---");
        System.out.println("Esperat: 0.0");
        System.out.println("Obtingut: " + saldoFinal);
        
        if (saldoFinal == 0.0f) {
            System.out.println("EXIT!");
            System.out.println("---------------");
        } else {
            System.out.println("error, balanç incorrecte");
        }
    }

    public Soci[] getSocis() {
        return socis;
    }

    public void setSocis(Soci[] socis) {
        this.socis = socis;
    }

    

}
