package c.waiter_solution;

import static c.waiter_solution.SolutionToPhilospherProblem.*;

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
                    rivalrySemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " ACQUIRED SEMAPHORE");
                } catch (InterruptedException e) {
                    System.out.println("Acquire");
                }
                eatingCounter++;
                rightFork.uplift();
                System.out.println("RIGHT");
                leftFork.uplift();
                System.out.println("LEFT");
                // eating
                System.out.println(Thread.currentThread().getName() + " has already eaten " + eatingCounter + " times");

                // end of eating
                rightFork.putOff();
                leftFork.putOff();

                System.out.println(Thread.currentThread().getName() + " FINISHED EATING");

                rivalrySemaphore.release();
                try {
                    sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
