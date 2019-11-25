package app;

import client.Consumer;
import client.Producer;
import server.Scheduler;
import utils.ProxyBuffer;

public class ActiveObject {

    private static void run(int capacity) {
        Scheduler scheduler = new Scheduler();
        scheduler.start();
        ProxyBuffer proxyBuffer = new ProxyBuffer(scheduler, capacity);

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