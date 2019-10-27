package zad1._2.b;

import zad1._2.Buffer;
import utils.BinarySemaphore;

import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    private final Semaphore full;
    private final Semaphore free;
    private final BinarySemaphore accessSemaphore;
    private Buffer buffer;
    private int producedAmount;
    private int i;

    public Producer(Semaphore full, Semaphore free, BinarySemaphore accessSemaphore, Buffer buffer, int producedAmount, int i) {
        this.full = full;
        this.free = free;
        this.accessSemaphore = accessSemaphore;
        this.buffer = buffer;
        this.producedAmount = producedAmount;
        this.i = i;
    }

    public void run() {
        Thread.currentThread().setName("Producer " + i);
        for (int i = 0; i < producedAmount; ++i) {
            try {
                free.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            accessSemaphore.P();
            buffer.put(i);
            accessSemaphore.V();
            full.release();
        }
    }
}