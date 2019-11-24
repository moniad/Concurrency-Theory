package utils;

import server.GetMethodRequest;
import server.PutMethodRequest;
import server.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static utils.ActiveObject.executorService;


public class ProxyBuffer {
    public static List<Integer> buffer;
    private Scheduler scheduler;
    public static int capacity;

    public ProxyBuffer(Scheduler scheduler, int capacity) {
        ProxyBuffer.capacity = capacity;
        buffer = new ArrayList<>();
        this.scheduler = scheduler;
    }

    public Future<Integer> put(int el) { // creates Method Request and puts it in ActivationQueue
        return executorService.submit(() -> {
            PutMethodRequest methodRequest = new PutMethodRequest(el);
            scheduler.enqueue(methodRequest);
            return el;
        });
    }

    public Future<Integer> get() {
        return executorService.submit(() -> {
            GetMethodRequest methodRequest = new GetMethodRequest();
            scheduler.enqueue(methodRequest);
            return methodRequest.getElem();
        });
    }
}
