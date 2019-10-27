package zad1._1;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private List<Integer> buffer;
    private int maxCapacity;
    private boolean canAccess;

    public Buffer(int capacity) {
        this.buffer = new ArrayList<>();
        this.maxCapacity = capacity;
        this.canAccess = true;
    }

    public synchronized void put(int i) {
        System.out.println("PRODUCER: Trying to produce");
        while (!canAccess || buffer.size() + 1 > maxCapacity) {
            try {
                System.out.println("PRODUCER: waiting to produce");
                canAccess = true;
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        canAccess = false;

        this.buffer.add(i);
        System.out.println("PRODUCED " + i);

        System.out.println("Current buffer state: ");
        for (int el : buffer) {
            System.out.println("EL: " + el);
        }

        canAccess = true;
        notifyAll();
    }

    public synchronized int get() {
        System.out.println("CONSUMER: Trying to consume");
        while (!canAccess || buffer.size() -1 < 0) {
            try {
                System.out.println("CONSUMER: Waiting to consume");
                canAccess = true;
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        canAccess = false;

        int returnVal = buffer.get(0);
        buffer.remove(0);

        if(buffer.size() > 0) {
            System.out.println("Current buffer state: ");
            for (int el : buffer) {
                System.out.println("EL: " + el);
            }
        }

        canAccess = true;
        notifyAll();
        return returnVal;
    }
}