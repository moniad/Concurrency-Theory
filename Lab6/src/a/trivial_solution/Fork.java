package a.trivial_solution;

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
        } catch (InterruptedException e) {
            System.out.println("uplift()");
        }
    }
    public void putOff() {
        accessSemaphore.release();
    }
}
