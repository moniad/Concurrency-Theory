import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private static List<String> buffer;
    static int maxCapacity;

    public Buffer(int maxCapacity) {
        buffer = new ArrayList<>();
        Buffer.maxCapacity = maxCapacity;
    }

    public synchronized void put(List<String> elems) {
        buffer.addAll(elems);
        System.out.println("PRODUCED " + elems);

        System.out.println("BUFFER IS FILLED UP TO " + this.getFullPlacesCount());
    }

    public synchronized List<String> get(int elemCount) {
        if (buffer.size() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Rozmiar bufora zero, nie wolno sięgać :(");
            }
        }
        List<String> elems = new ArrayList<>();

        for (int i = 0; i < elemCount; i++) {
            elems.add(buffer.get(0));
            buffer.remove(0);
        }

        System.out.println("BUFFER IS FILLED UP TO " + this.getFullPlacesCount());

        return elems;
    }

    public int getFreePlacesCount() {
        return Buffer.maxCapacity - buffer.size();
    }

    public int getFullPlacesCount() {
        return buffer.size();
    }
}
