package utils;

import client.Consumer;
import client.Producer;
import server.Scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ActiveObject {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static void run(int capacity) {
        Scheduler scheduler = new Scheduler();
        scheduler.start();
        ProxyBuffer proxyBuffer = new ProxyBuffer(scheduler);

        Semaphore free = new Semaphore(capacity);
        Semaphore full = new Semaphore(0);

        Producer p = new Producer(proxyBuffer, 100);

        Consumer c = new Consumer(proxyBuffer, 100);

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
        ActiveObject.run(10); // n1 == n2 == 1
    }
}