import java.util.Scanner;
public class Coet {
    private Motor[] motors = new Motor[4];
    public void arranca() {
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor(i);
            motors[i].start();
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: La potencia ha de ser entre 0 i 10");
            return;
        }
        System.out.println("Passant a potencia " + p);
        for (Motor m : motors) {
            m.setPotencia(p);
        }
    }
    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int potencia = scanner.nextInt();
                
                coet.passaAPotencia(potencia);
                if (potencia == 0) {
                    break;
                }
            }
        }
        scanner.close();
    }
}