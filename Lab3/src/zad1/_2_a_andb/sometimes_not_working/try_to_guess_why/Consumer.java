package zad1._2_a_andb.sometimes_not_working.try_to_guess_why;

public class Consumer extends Thread {
    private final CountingSemaphore full;
    private final CountingSemaphore empty;
    private BinarySemaphore accessSemaphore;
    private Buffer buffer;
    private int consumedAmount;

    public Consumer(CountingSemaphore full, CountingSemaphore empty, BinarySemaphore accessSemaphore, Buffer buffer, int consumedAmount) {
        this.full = full;
        this.empty = empty;
        this.accessSemaphore = accessSemaphore;
        this.buffer = buffer;
        this.consumedAmount = consumedAmount;
    }

    public void run() {
        for (int i = 0; i < consumedAmount; ++i) {
            full.P();
            accessSemaphore.P();
            System.out.println("CONSUMED: " + buffer.get());
            accessSemaphore.V();
            empty.V();
        }
    }
}
