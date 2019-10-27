package zad1._1.a;

import zad1._1.Buffer;
import zad1._1.Consumer;
import zad1._1.Producer;

public class PKmon {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Producer producer = new Producer(buffer, 100);
        Consumer consumer = new Consumer(buffer, 100);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}