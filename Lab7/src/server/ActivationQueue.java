package server;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActivationQueue { // kolejka aktywacji
    private Deque<MethodRequest> deque = new ArrayDeque<>();

    void enqueue(MethodRequest methodRequest) {
        deque.add(methodRequest);
    }

    MethodRequest dequeue() {
        return deque.removeFirst();
    }
}
