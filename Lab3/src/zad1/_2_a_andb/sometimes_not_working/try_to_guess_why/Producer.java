package zad1._2_a_andb.sometimes_not_working.try_to_guess_why;

public class Producer extends Thread {
    private final CountingSemaphore full;
    private final CountingSemaphore empty;
    private BinarySemaphore accessSemaphore;
    private Buffer buffer;
    private int producedAmount;

    public Producer(CountingSemaphore full, CountingSemaphore empty, BinarySemaphore accessSemaphore, Buffer buffer, int producedAmount) {
        this.full = full;
        this.empty = empty;
        this.accessSemaphore = accessSemaphore;
        this.buffer = buffer;
        this.producedAmount = producedAmount;
    }

    public void run() {
        for (int i = 0; i < producedAmount; ++i) {
            empty.P();
            accessSemaphore.P();
            buffer.put(i);
            accessSemaphore.V();
            full.V();
        }
    }
}