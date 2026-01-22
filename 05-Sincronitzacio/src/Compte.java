public class Compte {
    private static Compte instance;
    private Float saldo;

    private Compte(){
        this.saldo = 0f;
    }
    public Float getSaldo() {
        return saldo;
    }

    public void ingres(int saldo) {
        this.saldo += saldo;
    }

    public void retira(int retira){
        this.saldo -= retira;
    }

    public static synchronized Compte getInstance() {
        //Si no existe la crea
        if (instance == null) {
            instance = new Compte();
        }
        return instance; // Si existe devuelve la misma.
    }

}
