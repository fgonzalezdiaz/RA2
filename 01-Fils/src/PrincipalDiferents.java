public class PrincipalDiferents {
    public static void main(String[] args) {
        Fil f1 = new Fil("juan");
        Fil f2 = new Fil("pepe");

        f1.setPriority(Thread.MAX_PRIORITY);
        f2.setPriority(Thread.MIN_PRIORITY);

        f2.start();
        f1.start();

        System.out.println("Acaba thread main");
    }
}
