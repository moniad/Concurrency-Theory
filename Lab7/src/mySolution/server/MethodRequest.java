package mySolution.server;

public abstract class MethodRequest {
//        implements Callable<Integer> {
    public boolean guard() { // checks if synchronization conditions are fulfilled
        return false;
    }

    public void call() {
    }
}
