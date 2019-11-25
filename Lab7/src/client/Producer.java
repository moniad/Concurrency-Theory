package client;

import utils.ProxyBuffer;

public class Producer extends Thread {
    private ProxyBuffer proxyBuffer;
    private int producedAmount;

    public Producer(ProxyBuffer proxyBuffer, int producedAmount) {
        this.proxyBuffer = proxyBuffer;
        this.producedAmount = producedAmount;
    }

    public void run() {
        Thread.currentThread().setName("Producer");
        int elem;
        for (int i = 0; i < producedAmount; i++) {
            elem = i;
            proxyBuffer.put(i);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }

            System.out.println("PRODUCED: " + elem);
        }

        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}