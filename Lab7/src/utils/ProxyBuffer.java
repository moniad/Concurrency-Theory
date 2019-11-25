package utils;

import server.GetMethodRequest;
import server.PutMethodRequest;
import server.Scheduler;

import java.util.ArrayList;
import java.util.List;


public class ProxyBuffer {
    public static List<Integer> buffer;
    private Scheduler scheduler;
    public static int capacity;

    public ProxyBuffer(Scheduler scheduler, int capacity) {
        ProxyBuffer.capacity = capacity;
        buffer = new ArrayList<>();
        this.scheduler = scheduler;
    }

    public void put(int el) { // creates Method Request and puts it in ActivationQueue
        try {
            scheduler.enqueue(new PutMethodRequest(el));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CustomFuture get() {
        CustomFuture customFuture = new CustomFuture();
        try {
            scheduler.enqueue(new GetMethodRequest(customFuture));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return customFuture;
    }
}
