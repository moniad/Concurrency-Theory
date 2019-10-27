package zad1._2_a_andb.sometimes_not_working.try_to_guess_why.b;

import zad1._2_a_andb.sometimes_not_working.try_to_guess_why.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class PKmon {
    private static void runWhenMoreThanOneConsumerAndProducer(int capacity) {
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

        int p = 3, c = 6;
        ExecutorService executorService = Executors.newFixedThreadPool(p + c);

        for (int i = 0; i < p; i++) {
            executorService.submit(new Producer(taken, free, accessSemaphore, buffer, 200));
        }

        for (int i = 0; i < c; i++) {
            executorService.submit(new Consumer(taken, free, accessSemaphore, buffer, 100));
        }

        executorService.shutdown();
    }

    public static void main(String[] args) {
        PKmon.runWhenMoreThanOneConsumerAndProducer(10); // n1 > n2
    }
}
