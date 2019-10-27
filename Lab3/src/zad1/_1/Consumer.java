package zad1._1;

public class Consumer extends Thread {
    private Buffer buffer;
    private int consumedAmount;

    public Consumer(Buffer buffer, int consumedAmount) {
        this.buffer = buffer;
        this.consumedAmount = consumedAmount;
    }

    public void run() {
        for (int i = 0; i < consumedAmount; ++i) {
            System.out.println("CONSUMED: " + buffer.get());
            // 1.c
            try {
                sleep((long) (Math.random() % 10));
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}