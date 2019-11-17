package b.two_forks_lifted_up_simultaneously;

import java.util.concurrent.Semaphore;

public class Fork {
    private int forkNumber;
    private boolean isTaken;
    private Semaphore accessSemaphore;

    public Fork(int forkNumber) {
        this.forkNumber = forkNumber;
        isTaken = false;
        this.accessSemaphore = new Semaphore(1);
    }

    public void uplift() {
            isTaken = true;
    }
    public void putOff() {
        isTaken = false;
    }

    public boolean isTaken() {
        return isTaken;
    }
}
