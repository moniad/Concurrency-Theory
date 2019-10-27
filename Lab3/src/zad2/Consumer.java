package zad2;

import static zad2.PKmon.consumerAccessSemaphore;
import static zad2.PKmon.pipeBuffer;

public class Consumer extends Thread {
    private int consumedAmount;

    public Consumer(int consumedAmount) {
        this.consumedAmount = consumedAmount;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Consumer");
        for (int i = 0; i < consumedAmount; ++i) {
            consumerAccessSemaphore.P();

            System.out.println("CONSUMED " + pipeBuffer);
            pipeBuffer = -1;

            PKmon.pipeBufferAccessSemaphores.get("bs0").V();
        }
    }
}