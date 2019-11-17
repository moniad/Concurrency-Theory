package c.waiter_solution;

import java.util.concurrent.Semaphore;

public class Fork {
    private int forkNumber;
    private Semaphore accessSemaphore;

    public Fork(int forkNumber) {
        this.forkNumber = forkNumber;
        this.accessSemaphore = new Semaphore(1);
    }

    public void uplift() {
        try {
            accessSemaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " LIFTED UP FORK");
        } catch (InterruptedException e) {
            System.out.println("uplift()");
        }
    }
    public void putOff() {
        System.out.println(Thread.currentThread().getName() + " PUT OFF FORK");
        accessSemaphore.release();
    }
}
