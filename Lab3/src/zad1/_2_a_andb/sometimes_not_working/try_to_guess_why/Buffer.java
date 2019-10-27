package zad1._2_a_andb.sometimes_not_working.try_to_guess_why;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private List<Integer> buffer;
    private static int capacity;

    public Buffer(int _capacity) {
        capacity = _capacity;
        this.buffer = new ArrayList<>();
    }

    public void put(int i) {
        this.buffer.add(i);
        System.out.println("PRODUCED " + i);

        System.out.println("Current buffer state: ");
        for (int el : buffer) {
            System.out.println("EL: " + el);
        }
    }

    public int get() {
        if (buffer.size() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Rozmiar bufora zero, nie wolno sięgać :(");
            }
        }
        int returnVal = buffer.get(0);
        buffer.remove(0);

        if (buffer.size() > 0) {
            System.out.println("Current buffer state: ");
            for (int el : buffer) {
                System.out.println("EL: " + el);
            }
        }

        return returnVal;
    }
}
