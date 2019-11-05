import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    private final Semaphore full;
    private final Semaphore free;
    private Buffer buffer;
    private int producedAmount;
    private int threadNumber;
    private ThreadLocal<Long> startTime;
    private ThreadLocal<Long> endTime;

    public Producer(Semaphore full, Semaphore free, Buffer buffer, int producedAmount, int threadNumber) {
        this.full = full;
        this.free = free;
        this.buffer = buffer;
        this.producedAmount = producedAmount;
        this.threadNumber = threadNumber;
        startTime = new ThreadLocal<>();
        endTime = new ThreadLocal<>();
    }

    public void run() {
        startTime.set(System.currentTimeMillis());
        PKmon.lock.lock();
        while (buffer.getFreePlacesCount() < producedAmount) {
            try {
                System.out.format("Producer %d is waiting to produce %d amount of products\n", threadNumber, producedAmount);
                PKmon.lock.unlock();
                free.acquire();
                PKmon.lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("BUFFER IS FILLED UP TO " + buffer.getFullPlacesCount() + " vs producedAmount: " + producedAmount);
        List<String> elems = new ArrayList<>();
        for (int i = 0; i < producedAmount; i++) {
            elems.add(String.format("Item no. %d Produced by %d\n", i, threadNumber));
        }
        buffer.put(elems);
        endTime.set(System.currentTimeMillis());
        PKmon.lock.unlock();
        full.release();

        PKmon.producerWaitingTime.addAndGet(endTime.get() - startTime.get());
        PKmon.producersWhoFinishedCount.addAndGet(1);
    }
}