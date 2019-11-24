package server;

import utils.ProxyBuffer;

public class Servant { // pracownik
    public void put(int el) {
        ProxyBuffer.buffer.add(el);
        System.out.println("PRODUCED " + el);


//        System.out.println("Current buffer state: ");
//        for (int el : buffer) {
//            System.out.println("EL: " + el);
//        }
    }

    public int get() {
        if (ProxyBuffer.buffer.size() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Rozmiar bufora zero, nie wolno sięgać :(");
            }
        }
        Integer returnVal = ProxyBuffer.buffer.get(0);
        ProxyBuffer.buffer.remove(0);

//        if (buffer.size() > 0) {
//            System.out.println("Current buffer state: ");
//            for (int el : buffer) {
//                System.out.println("EL: " + el);
//            }
//        }
        return returnVal;
    }
}
