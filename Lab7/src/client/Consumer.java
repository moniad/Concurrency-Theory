package client;

import utils.ProxyBuffer;

import java.util.concurrent.*;

public class Consumer extends Thread {
    private ProxyBuffer proxyBuffer;
    private int consumedAmount;

    public Consumer(ProxyBuffer proxyBuffer, int consumedAmount) {
        this.proxyBuffer = proxyBuffer;
        this.consumedAmount = consumedAmount;
    }

    public void run() {
        Future<Integer> future;
        for (int i = 0; i < consumedAmount; ++i) {
            future = proxyBuffer.get();

            while (!future.isDone()) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }

            try {
                Integer result = future.get(10, TimeUnit.SECONDS);
                System.out.println("CONSUMED: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("CONSUMER HASN'T RECEIVED ANSWER FOR 10 SEC");
            }
        }
    }
}
