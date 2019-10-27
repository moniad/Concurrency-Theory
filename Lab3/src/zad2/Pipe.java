package zad2;

public class Pipe implements Runnable {
    private final int threadNumber;

    public Pipe(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Pipe " + this.threadNumber);
        for (int i = 0; i < PKmon.producedCount; i++) {
            if (threadNumber == 0) {
                try {
                    PKmon.producerBufferFullSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int item = PKmon.producerBuffer.get();
                PKmon.producerBufferEmptySemaphore.release(); // consumer could do it instead

                PKmon.pipeBufferAccessSemaphores.get("bs0").P();
                if (PKmon.pipeBuffer != -1) {
                    throw new IllegalStateException("Sth is already in buffer!");
                }
                PKmon.pipeBuffer = item;
                System.out.format("THREAD: %s. Current buffer content: %d\n", Thread.currentThread().getName(), PKmon.pipeBuffer);

                PKmon.pipeBufferAccessSemaphores.get("bs1").V();
            } else if (threadNumber == PKmon.pipesCount - 1) {
                PKmon.pipeBufferAccessSemaphores.get("bs" + this.threadNumber).P();

                int item = PKmon.pipeBuffer;
                PKmon.pipeBuffer = -1;
                PKmon.pipeBuffer = item;

                System.out.format("THREAD: %s. Current buffer content: %d\n", Thread.currentThread().getName(), PKmon.pipeBuffer);

                PKmon.consumerAccessSemaphore.V();
            } else {
                PKmon.pipeBufferAccessSemaphores.get("bs" + this.threadNumber).P();

                int item = PKmon.pipeBuffer;
                PKmon.pipeBuffer = -1;
                PKmon.pipeBuffer = item;

                System.out.format("THREAD: %s. Current buffer content: %d\n", Thread.currentThread().getName(), PKmon.pipeBuffer);

                PKmon.pipeBufferAccessSemaphores.get("bs" + (this.threadNumber + 1)).V();
            }
        }
    }
}
