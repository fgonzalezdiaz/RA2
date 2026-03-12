public class Barri {
    public static void main(String[] args) {
        Estanc estanc = new Estanc();
        Fumador f0 = new Fumador(estanc, 0);
        Fumador f1 = new Fumador(estanc, 1);
        Fumador f2 = new Fumador(estanc, 2);

        f0.start();
        f1.start();
        f2.start();
        estanc.start();

        try {
            f0.join();
            f1.join();
            f2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        estanc.tancarEstanc();
    }
}
