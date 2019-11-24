package server;

public class Scheduler extends Thread {
    static ActivationQueue activationQueue;

    @Override
    public void run() {

    }

    public void dispatch() { //todo

    }

    public void enqueue(MethodRequest methodRequest) {
        activationQueue.enqueue(methodRequest);
    }
}
