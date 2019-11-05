import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class PKmon {
    private static List<Integer> histogram;
    static Buffer buffer;
    static Semaphore fullSemaphore;
    static Semaphore emptySemaphore;
    static final ReentrantLock lock = new ReentrantLock();
    static AtomicLong consumerWaitingTime;
    static AtomicLong producerWaitingTime;
    static AtomicInteger producersWhoFinishedCount;
    static AtomicInteger consumersWhoFinishedCount;

    public PKmon(int bufferCapacity) {
        histogram = new ArrayList<>();
        buffer = new Buffer(bufferCapacity);
        fullSemaphore = new Semaphore(0);
        emptySemaphore = new Semaphore(bufferCapacity);
        consumerWaitingTime = new AtomicLong(0);
        producerWaitingTime = new AtomicLong(0);
        producersWhoFinishedCount = new AtomicInteger(0);
        consumersWhoFinishedCount = new AtomicInteger(0);
    }

    private void process(int producerCount, int consumerCount) {
        System.out.println("ProducerCount: " + producerCount + ", consumerCount: " + consumerCount);
        ExecutorService executorService = Executors.newFixedThreadPool(producerCount + consumerCount);
        Random generator = new Random();

        int randomNumber;
        for (int i = 0; i < producerCount; i++) {
            randomNumber = generator.nextInt(Buffer.maxCapacity / 2) + 1;
            System.out.println("PRODUCER " + i + " -> " + randomNumber);
            executorService.submit(new Producer(fullSemaphore, emptySemaphore, buffer, randomNumber, i));
        }

        for (int i = 0; i < consumerCount; i++) {
            randomNumber = generator.nextInt(Buffer.maxCapacity / 2) + 1;
            System.out.println("CONSUMER " + i + " -> " + randomNumber);
            executorService.submit(new Consumer(fullSemaphore, emptySemaphore, buffer, randomNumber, i));
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Still waiting after 10s: calling System.exit(0)...");
                handleData();
                try {
                    saveDataToFile("/home/monika/IdeaProjects/TW/Lab4/histogram");
                } catch (IOException e) {
                    System.out.println("Could not save file");
                }
                System.exit(0);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Exiting normally...");
        handleData();
    }

    private void handleData() {
        System.out.printf("\n\nproducersWhoFinishedCount: %s, consumersWhoFinishedCount: %s. Average producers time: %d%n vs average consumers time: %d%n\n",
                producersWhoFinishedCount, consumersWhoFinishedCount,
                producersWhoFinishedCount.get() == 0 ? 0 : producerWaitingTime.get() / producersWhoFinishedCount.get(),
                consumersWhoFinishedCount.get() == 0 ? 0 : consumerWaitingTime.get() / consumersWhoFinishedCount.get());

        histogram.add(producersWhoFinishedCount.get());
        histogram.add(consumersWhoFinishedCount.get());
        histogram.add((int) producerWaitingTime.get());
        histogram.add((int) consumerWaitingTime.get());
    }

    private static void saveDataToFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Integer elem : histogram) {
            bufferedWriter.write(elem.toString() + " ");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static int generateRandomInt(Random generator, int upperBound) {
        return generator.nextInt(upperBound);
    }

    public static void main(String[] args) {
        PKmon pkmon = new PKmon(150);
        Random generator = new Random();

        pkmon.process(13, 10);
//                    generateRandomInt(generator, 25) + 1, generateRandomInt(generator, 25) + 1);
        try {
            saveDataToFile("/home/monika/IdeaProjects/TW/Lab4/histogram");
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }
}
