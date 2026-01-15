public class PrincipalIguals{
    public static void main(String[] args) {
        Fil f1 = new Fil("juan");
        Fil f2 = new Fil("pepe");
        
        f1.setPriority(Thread.MIN_PRIORITY);
        f2.setPriority(Thread.MAX_PRIORITY);
        
        f1.start();
        f2.start();

        System.out.println("Acaba thread main");
    }
}