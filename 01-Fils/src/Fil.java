public class Fil extends Thread {
    public Fil (String nom) {
        super(nom);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(getName() + " " + i);
            /*for(int j = 0; j < 80000; j++) {
               // Aquest for s'utilitza al comportament 1 y 2
            }*/

            /*try {
                // Aquest try-catch s'utilitza al comportament 3
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("Acaba el fil " + getName());
    }

}
