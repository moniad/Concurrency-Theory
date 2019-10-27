package zad1._2.b;

import zad1._2.Buffer;
import utils.BinarySemaphore;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private final Semaphore full;
    private final Semaphore free;
    private final BinarySemaphore accessSemaphore;
    private Buffer buffer;
    private int consumedAmount;
    private int i;

    public Consumer(Semaphore full, Semaphore free, BinarySemaphore accessSemaphore, Buffer buffer, int consumedAmount, int i) {
        this.full = full;
        this.free = free;
        this.accessSemaphore = accessSemaphore;
        this.buffer = buffer;
        this.consumedAmount = consumedAmount;
        this.i = i;
    }

    public void run() {
        Thread.currentThread().setName("Consumer " + i);
        for (int i = 0; i < consumedAmount; ++i) {
            try {
                full.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            accessSemaphore.P();
            System.out.println("CONSUMED: " + buffer.get());
            accessSemaphore.V();
            free.release();
        }
    }
}
