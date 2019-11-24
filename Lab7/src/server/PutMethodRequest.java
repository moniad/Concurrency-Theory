package server;

public class PutMethodRequest extends MethodRequest {
    private int el;

    public PutMethodRequest(int el) {
        this.el = el;
    }

    @Override
    public boolean guard() {
//todo
        return true;
    }

    @Override
    public void call() {
        //todo
    }
}