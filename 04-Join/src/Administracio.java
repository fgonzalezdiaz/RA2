import java.util.ArrayList;

public class Administracio {
    private int num_poblacio_activa = 50;
    private ArrayList<Treballador> poblacio_activa = new ArrayList<>();
    
    
    public Administracio () {
        for (int i = 0; i < num_poblacio_activa; i++){
            String nom = "Ciutadà - " + i;
            this.poblacio_activa.add(new Treballador(nom));
        }
    }
    
    public int getNum_poblacio_activa() {
        return num_poblacio_activa;
    }
    public void setNum_poblacio_activa(int num_poblacio_activa) {
        this.num_poblacio_activa = num_poblacio_activa;
    }
    public ArrayList<Treballador> getPoblacio_activa() {
        return poblacio_activa;
    }
    public void setPoblacio_activa(ArrayList<Treballador> poblacio_activa) {
        this.poblacio_activa = poblacio_activa;
    }

    public static void main(String args[]){
        Administracio adm = new Administracio();
        for(Treballador trb : adm.poblacio_activa){
            trb.start();
            try {
                trb.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(Treballador trb : adm.poblacio_activa){
            System.out.println("Ciutadà-" + trb.getName() + " -> edat: " + trb.getEdat_actual() + " / total: " + trb.getCobrat());
        }

    }
    
}
