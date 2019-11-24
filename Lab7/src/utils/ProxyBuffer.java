package utils;

import server.GetMethodRequest;
import server.MethodRequest;
import server.PutMethodRequest;
import server.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static utils.ActiveObject.executorService;


public class ProxyBuffer {
    public static List<Integer> buffer;
    private Scheduler scheduler;

    public ProxyBuffer(Scheduler scheduler) {
        buffer = new ArrayList<>();
        this.scheduler = scheduler;
    }

    public Future<Integer> put(int el) { // creates Method Request and puts it in ActivationQueue
        return executorService.submit(() -> {
            MethodRequest methodRequest = new PutMethodRequest(el);
            scheduler.enqueue(methodRequest);
            return el; //todo ??
        });
    }

    public Future<Integer> get() {
        return executorService.submit(() -> {
            MethodRequest methodRequest = new GetMethodRequest();
            scheduler.enqueue(methodRequest);
            return -1; // todo ?????????
        });
    }
}
