package client;

import utils.ProxyBuffer;

import java.util.concurrent.*;

public class Producer extends Thread {
    private ProxyBuffer proxyBuffer;
    private int producedAmount;

    public Producer(ProxyBuffer proxyBuffer, int producedAmount) {
        this.proxyBuffer = proxyBuffer;
        this.producedAmount = producedAmount;
    }

    public void run() {
        Future<Integer> future;
        for (int i = 0; i < producedAmount; ++i) {
            future = proxyBuffer.put(i);

            while (!future.isDone()) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }

            try {
                System.out.println("PRODUCED: " + future.get(500, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("PRODUCER HASN'T RECEIVED ANSWER FOR 10 SEC");
            }
        }
    }
}