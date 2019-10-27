package zad1._2_a_andb.sometimes_not_working.try_to_guess_why.a;

import zad1._2_a_andb.sometimes_not_working.try_to_guess_why.*;

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

        Buffer buffer = new Buffer(capacity);
        CountingSemaphore free = new CountingSemaphore(capacity);
        CountingSemaphore taken = new CountingSemaphore(0);
        BinarySemaphore accessSemaphore = new BinarySemaphore(true);

        Producer p = new Producer(taken, free, accessSemaphore, buffer, 100);

        Consumer c = new Consumer(taken, free, accessSemaphore, buffer, 100);

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
