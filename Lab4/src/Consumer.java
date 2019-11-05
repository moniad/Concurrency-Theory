import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private final Semaphore full;
    private final Semaphore free;
    private Buffer buffer;
    private int consumedAmount;
    private int threadNumber;
    private ThreadLocal<Long> startTime;
    private ThreadLocal<Long> endTime;

    public Consumer(Semaphore full, Semaphore free, Buffer buffer, int consumedAmount, int threadNumber) {
        this.full = full;
        this.free = free;
        this.buffer = buffer;
        this.consumedAmount = consumedAmount;
        this.threadNumber = threadNumber;
        startTime = new ThreadLocal<>();
        endTime = new ThreadLocal<>();
    }

    public void run() {
        startTime.set(System.currentTimeMillis());
        PKmon.lock.lock();
        while (buffer.getFullPlacesCount() < consumedAmount) {
            try {
                System.out.format("Consumer %d is waiting to consume %d amount of products\n", threadNumber, consumedAmount);
                PKmon.lock.unlock();
                full.acquire();
                PKmon.lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("BEFORE: BUFFER IS FILLED UP TO " + buffer.getFullPlacesCount() + " vs consumedAmount: " + consumedAmount);
        System.out.format("%s Consumed by %d\n", buffer.get(consumedAmount), threadNumber);
        endTime.set(System.currentTimeMillis());
        PKmon.lock.unlock();
        free.release();

        PKmon.consumerWaitingTime.addAndGet(endTime.get() - startTime.get());
        PKmon.consumersWhoFinishedCount.addAndGet(1);
    }
}
