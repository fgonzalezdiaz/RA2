import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private int nombrePlacesMaximes;
    private List<Assistent> llistaAssistents;

    public Esdeveniment(int nombrePlacesMaximes) {
        this.nombrePlacesMaximes = nombrePlacesMaximes;
        this.llistaAssistents = new ArrayList<>();
    }

    public synchronized void ferReserva(Assistent assistent) {
        //bucle while per gestionar notificacions.
        while (llistaAssistents.size() >= nombrePlacesMaximes) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(assistent.getName() + " ha sigut interromput mentres esperava.");
                return;
            }
        }

        // Si sortim del bucle, hi ha lloc
        llistaAssistents.add(assistent);
        System.out.println(assistent.getName() + " ha fet una reserva. Places disponibles: " + (nombrePlacesMaximes - llistaAssistents.size()));
    }

    public synchronized void cancelarReserva(Assistent assistent) {
        if (llistaAssistents.contains(assistent)) {
            llistaAssistents.remove(assistent);
            System.out.println(assistent.getName() + " ha cancel·lat una reserva. Places disponibles: " + (nombrePlacesMaximes - llistaAssistents.size()));

            // utilitzar notifyAll() per evitar bloqueos
            notifyAll();
        } else {
            System.out.println(
                    assistent.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + (nombrePlacesMaximes - llistaAssistents.size()));
        }
    }
}
