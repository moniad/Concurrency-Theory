package zad1._1;

public class Producer extends Thread {
    private Buffer buffer;
    private int producedAmount;

    public Producer (Buffer buffer, int producedAmount) {
        this.buffer = buffer;
        this.producedAmount = producedAmount;
    }

    public void run() {
        for (int i = 0; i < producedAmount; ++i) {
            buffer.put(i);
            // 1.c
            try {
                sleep((long) (Math.random() % 10));
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}