package mySolution.server;

import mySolution.utils.ProxyBuffer;

class Servant { // pracownik
    static void put(int el) {
        ProxyBuffer.buffer.add(el);
    }

    static int get() {
        Integer returnVal = ProxyBuffer.buffer.get(0);
        ProxyBuffer.buffer.remove(0);
        return returnVal;
    }

    static boolean isBufferEmpty() {
        return ProxyBuffer.buffer.isEmpty();
    }

    static boolean isBufferFull() {
        return ProxyBuffer.buffer.size() == ProxyBuffer.capacity;
    }
}
