public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }

    public Taula(int numFilosofs) {
        filosofs = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < filosofs.length; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % filosofs.length];
            filosofs[i] = new Filosof("fil" + i, esquerra, dreta);
        }
    }

    public void showTaula() {
        for (int i = 0; i < filosofs.length; i++) {
            System.out.println("Comensal: fil" + i + " esq:" + i + " dret:" + ((i + 1) % filosofs.length));
        }
    }

    public void cridarATaula() {
        for (Filosof filosof : filosofs) {
            filosof.start();
        }
    }

}
