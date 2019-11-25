package server;

public abstract class MethodRequest {
    public boolean guard() { // checks if synchronization conditions are fulfilled
        return false;
    }

    public void call() {
    }
}
