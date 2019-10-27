package zad2;

public class Producer extends Thread {
    private int producedAmount;

    public Producer(int producedAmount) {
        this.producedAmount = producedAmount;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Producer");
        for (int i = 0; i < producedAmount; ++i) {
            try {
                PKmon.producerBufferEmptySemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PKmon.producerBuffer.put(i);
            PKmon.producerBufferFullSemaphore.release();
        }
    }
}
