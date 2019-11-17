package a.trivial_solution;

import static a.trivial_solution.SolutionToPhilospherProblem.forks;
import static a.trivial_solution.SolutionToPhilospherProblem.philosopherCount;

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
                eatingCounter++;
                rightFork.uplift();
                leftFork.uplift();
                // eating
                System.out.println(Thread.currentThread().getName() +
                        " has already eaten " + eatingCounter + " times");

                // end of eating
                rightFork.putOff();
                leftFork.putOff();
            }
        }
    }
}
