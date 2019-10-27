package zad1._2.a;

import zad1._2.Buffer;

import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    private final Semaphore full;
    private final Semaphore free;
    private Buffer buffer;
    private int producedAmount;

    public Producer(Semaphore full, Semaphore free, Buffer buffer, int producedAmount) {
        this.full = full;
        this.free = free;
        this.buffer = buffer;
        this.producedAmount = producedAmount;
    }

    public void run() {
        for (int i = 0; i < producedAmount; ++i) {
            try {
                free.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.put(i);
            full.release();
        }
    }
}