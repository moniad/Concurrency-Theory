package mySolution.client;

import mySolution.utils.CustomFuture;
import mySolution.utils.ProxyBuffer;

public class Consumer extends Thread {
    private ProxyBuffer proxyBuffer;
    private int consumedAmount;

    public Consumer(ProxyBuffer proxyBuffer, int consumedAmount) {
        this.proxyBuffer = proxyBuffer;
        this.consumedAmount = consumedAmount;
    }

    public void run() {
        Thread.currentThread().setName("Consumer");
        CustomFuture consumed;
        for (int i = 0; i < consumedAmount; ++i) {
            consumed = proxyBuffer.get();

            while (!consumed.isReady()) {
                System.out.println(Thread.currentThread().getName() + " waits...");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
            System.out.println("CONSUMED: " + consumed.getObject());
        }

        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}
