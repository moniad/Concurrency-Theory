package b.two_forks_lifted_up_simultaneously;

import static b.two_forks_lifted_up_simultaneously.SolutionToPhilospherProblem.*;

public class Philosopher extends Thread {
    private int counter;
    private int eatingCounter;
    private int threadNumber;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int threadNumber) {
        this.counter = 0;
        this.eatingCounter = 0;
        this.threadNumber = threadNumber;
        this.leftFork = forks.get(threadNumber);
        this.rightFork = forks.get((threadNumber + 1) % philosopherCount);
    }

    public void run() {
        Thread.currentThread().setName("Philosopher " + threadNumber);
        while (true) {
            ++counter;
            if (counter % 100 == 0) {
                try {
                    do {
                        forksAccessSemaphore.acquire();
                        if (leftFork.isTaken() || rightFork.isTaken()) {
                            forksAccessSemaphore.release();
                        } else {
                            break;
                        }
                    }
                    while (true);
                } catch (InterruptedException e) {
                    System.out.println("Acquire");
                }

                eatingCounter++;
                rightFork.uplift();
                leftFork.uplift();
                // eating
                System.out.println(Thread.currentThread().getName() +
                        " has already eaten " + eatingCounter + " times");

                // end of eating
                rightFork.putOff();
                leftFork.putOff();

                forksAccessSemaphore.release();
                try {
                    sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
