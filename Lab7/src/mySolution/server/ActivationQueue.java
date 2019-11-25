package mySolution.server;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

class ActivationQueue { // kolejka aktywacji
    private Deque<MethodRequest> deque;
    private Semaphore mutex;

    ActivationQueue() {
        this.deque = new ArrayDeque<>();
        this.mutex = new Semaphore(1);
    }

    void enqueue(MethodRequest methodRequest) throws InterruptedException {
        mutex.acquire();
        deque.add(methodRequest);
        mutex.release();
    }

    MethodRequest dequeueFirst() {
        if (!deque.isEmpty()) {
            return deque.removeFirst();
        }
//        System.out.println("EMPTY QUEUE - FIRST!!!!");
        return null;
    }

    MethodRequest dequeueLast() {
        if (!deque.isEmpty()) {
            return deque.removeLast();
        }
//        System.out.println("EMPTY QUEUE - LAST!!!!");
        return null;
    }
}
