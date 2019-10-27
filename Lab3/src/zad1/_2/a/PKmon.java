package zad1._2.a;

import zad1._2.Buffer;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class PKmon {
    private static void runWhenOneProducerAndConsumer(int capacity) {
        System.out.println("Running " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "\n\n\n");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        Buffer buffer = new Buffer();
        Semaphore free = new Semaphore(capacity);
        Semaphore full = new Semaphore(0);

        Producer p = new Producer(full, free, buffer, 100);

        Consumer c = new Consumer(full, free, buffer, 100);

        p.start();
        c.start();

        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PKmon.runWhenOneProducerAndConsumer(10); // n1 == n2 == 1
    }
}
