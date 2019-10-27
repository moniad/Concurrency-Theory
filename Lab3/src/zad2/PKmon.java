package zad2;

import utils.BinarySemaphore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PKmon {
    private static int producerCount = 1;
    static int pipesCount = 5;
    private static int consumerCount = 1;
    private static int capacity = 3;
    static int producedCount = 100;
    static Buffer producerBuffer;
    static int pipeBuffer;
    static Semaphore producerBufferFullSemaphore;
    static Semaphore producerBufferEmptySemaphore;
    static Map<String, BinarySemaphore> pipeBufferAccessSemaphores;
    static BinarySemaphore consumerAccessSemaphore;

    public PKmon() {
        producerBuffer = new Buffer();
        producerBufferFullSemaphore = new Semaphore(0);
        producerBufferEmptySemaphore = new Semaphore(capacity);
        pipeBuffer = -1;
        pipeBufferAccessSemaphores = new HashMap<>();

        pipeBufferAccessSemaphores.put("bs0", new BinarySemaphore(true));
        pipeBufferAccessSemaphores.put("bs1", new BinarySemaphore(false));
        pipeBufferAccessSemaphores.put("bs2", new BinarySemaphore(false));
        pipeBufferAccessSemaphores.put("bs3", new BinarySemaphore(false));
        pipeBufferAccessSemaphores.put("bs4", new BinarySemaphore(false));

        consumerAccessSemaphore = new BinarySemaphore(false);
    }

    private void process() {
        ExecutorService executorService = Executors.newFixedThreadPool(producerCount + pipesCount + consumerCount);

        for (int i = 0; i < producerCount; i++) {
            executorService.submit(new Producer(producedCount));
        }

        for (int i = 0; i < pipesCount; i++) {
            executorService.submit(new Pipe(i));
        }

        for (int i = 0; i < consumerCount; i++) {
            executorService.submit(new Consumer(producedCount));
        }

        executorService.shutdown();
    }

    public static void main(String[] args) {
        PKmon pkmon = new PKmon();
        pkmon.process();
    }
}
