package zad1._2_a_andb.sometimes_not_working.try_to_guess_why;

public class BinarySemaphore {
    private boolean isFree;

    public BinarySemaphore(boolean free) {
        this.isFree = free;
    }

    public synchronized void P() { // opuszczenie semafora
        while (!isFree) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }
        isFree = false;
    }

    public synchronized void V() { // podniesienie semafora
        isFree = true;
        notify();
    }
}
