package utils;

public class BinarySemaphore {
    private boolean isFree;

    public BinarySemaphore(boolean free) {
        this.isFree = free;
    }

    public synchronized void P() { // opuszczenie semafora
        while (!isFree) {
            try {
//                System.out.println("THREAD: " + Thread.currentThread().getName() + " will wait");
                wait();
                System.out.println("THREAD: " + Thread.currentThread().getName() + " woke up");
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }
        isFree = false;
    }

    public synchronized void V() { // podniesienie semafora
        isFree = true;
        notifyAll();
    }
}
