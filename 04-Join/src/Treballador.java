import java.util.Random;

public class Treballador extends Thread {
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(){

    }

    public Treballador(String nom) {
        super(nom);
        this.sou_anual_brut = 25000;
        this.edat_inici_treball = 20;
        this.edat_fi_treball = 65;
        this.edat_actual = 20;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    @Override
    public void run(){
        while(edat_actual < edat_fi_treball){
            this.edat_actual++;

            if(edat_actual <= edat_fi_treball){
                for(int i = 0; i < 12; i++){
                    this.cobra();
                    this.pagaImpostos();
                }

                try {
                    Thread.sleep(this.rnd.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    public void cobra(){
        this.cobrat += this.sou_anual_brut / 12 ;
    }

    public void pagaImpostos(){
        this.cobrat -= (this.sou_anual_brut / 12) * 0.24;
    }
    public float getSou_anual_brut() {
        return sou_anual_brut;
    }

    public void setSou_anual_brut(float sou_anual_brut) {
        this.sou_anual_brut = sou_anual_brut;
    }

    public int getEdat_inici_treball() {
        return edat_inici_treball;
    }

    public void setEdat_inici_treball(int edat_inici_treball) {
        this.edat_inici_treball = edat_inici_treball;
    }

    public int getEdat_fi_treball() {
        return edat_fi_treball;
    }

    public void setEdat_fi_treball(int edat_fi_treball) {
        this.edat_fi_treball = edat_fi_treball;
    }

    public int getEdat_actual() {
        return edat_actual;
    }

    public void setEdat_actual(int edat_actual) {
        this.edat_actual = edat_actual;
    }

    public float getCobrat() {
        return cobrat;
    }

    public void setCobrat(float cobrat) {
        this.cobrat = cobrat;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }
}
