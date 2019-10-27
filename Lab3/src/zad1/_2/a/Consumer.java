package zad1._2.a;

import zad1._2.Buffer;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private final Semaphore full;
    private final Semaphore free;
    private Buffer buffer;
    private int consumedAmount;

    public Consumer(Semaphore full, Semaphore free, Buffer buffer, int consumedAmount) {
        this.full = full;
        this.free = free;
        this.buffer = buffer;
        this.consumedAmount = consumedAmount;
    }

    public void run() {
        for (int i = 0; i < consumedAmount; ++i) {
            try {
                full.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CONSUMED: " + buffer.get());
            free.release();
        }
    }
}
