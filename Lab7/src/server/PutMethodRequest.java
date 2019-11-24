package server;

public class PutMethodRequest extends MethodRequest {
    private int elem;

    public PutMethodRequest(int elem) {
        this.elem = elem;
    }

    @Override
    public boolean guard() {
        return !Servant.isBufferFull();
    }

    @Override
    public void call() {
        Servant.put(elem);
        System.out.println("PUT ELEM " + elem);
//        return elem;
    }
}