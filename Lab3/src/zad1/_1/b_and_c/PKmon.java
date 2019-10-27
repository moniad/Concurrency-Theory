package zad1._1.b_and_c;

import zad1._1.Buffer;
import zad1._1.Consumer;
import zad1._1.Producer;

import static java.lang.Thread.sleep;

public class PKmon {
    private static void runWhenMoreProducersThanConsumers() {
        System.out.println("Running " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "\n\n\n");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        Buffer buffer = new Buffer(10);
        Producer p1 = new Producer(buffer, 100);
        Producer p2 = new Producer(buffer, 100);
        Producer p3 = new Producer(buffer, 100);
        Producer p4 = new Producer(buffer, 100);

        Consumer c1 = new Consumer(buffer, 200);
        Consumer c2 = new Consumer(buffer, 200);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        c1.start();
        c2.start();

        try {
            p1.join();
            p2.join();
            p3.join();
            p4.join();
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runWhenTheSameAmountOfProducersAsConsumers() {
        System.out.println("Running " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "\n\n\n");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        Buffer buffer = new Buffer(10);
        Producer p1 = new Producer(buffer, 50);
        Producer p2 = new Producer(buffer, 50);
        Producer p3 = new Producer(buffer, 50);

        Consumer c1 = new Consumer(buffer, 50);
        Consumer c2 = new Consumer(buffer, 50);
        Consumer c3 = new Consumer(buffer, 50);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

        try {
            p1.join();
            p2.join();
            p3.join();
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runWhenMoreConsumersThanProducers() {
        System.out.println("Running " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "\n\n\n");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        Buffer buffer = new Buffer(10);

        Producer p1 = new Producer(buffer, 300);
        Producer p2 = new Producer(buffer, 300);

        Consumer c1 = new Consumer(buffer, 100);
        Consumer c2 = new Consumer(buffer, 100);
        Consumer c3 = new Consumer(buffer, 100);
        Consumer c4 = new Consumer(buffer, 100);
        Consumer c5 = new Consumer(buffer, 100);
        Consumer c6 = new Consumer(buffer, 100);

        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();

        try {
            p1.join();
            p2.join();
            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();
            c6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        PKmon.runWhenMoreConsumersThanProducers(); // n1 > n2

        PKmon.runWhenTheSameAmountOfProducersAsConsumers(); // n1 == n2

//        PKmon.runWhenMoreProducersThanConsumers(); // n1 < n2
    }
}