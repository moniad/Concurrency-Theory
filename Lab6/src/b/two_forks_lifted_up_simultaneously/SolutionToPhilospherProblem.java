package b.two_forks_lifted_up_simultaneously;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SolutionToPhilospherProblem {
    static int philosopherCount;
    private static int forksCount;
    static ArrayList<Fork> forks;
    static Semaphore forksAccessSemaphore;
    private static ArrayList<Double> histogram;

    public SolutionToPhilospherProblem() {
        philosopherCount = 5;
        forksCount = 5;
        forks = new ArrayList<>();
        forksAccessSemaphore = new Semaphore(1);
        histogram = new ArrayList<>();
    }

    private void find() {
        ExecutorService executorService = Executors.newFixedThreadPool(philosopherCount);

        for (int i = 0; i < forksCount; i++) {
            forks.add(new Fork(i));
        }

        for (int i = 0; i < philosopherCount; i++) {
            executorService.submit(new Philosopher(i));
        }

        executorService.shutdown();
        try {
            saveDataToFile("/home/monika/IdeaProjects/TW/Lab6/two_forks.txt");
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    private static void saveDataToFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Double elem : histogram) {
            bufferedWriter.write(elem.toString() + " ");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void main(String[] args) {
        SolutionToPhilospherProblem solution = new SolutionToPhilospherProblem();
        solution.find();
    }
}
