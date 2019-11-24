package server;

public class GetMethodRequest extends MethodRequest {
    private int elem = -5000;

    public GetMethodRequest() {
    }

    @Override
    public boolean guard() {
        return !Servant.isBufferEmpty();
    }

    @Override
    public void call() {
        System.out.println("GET ELEM " + getElem());
        elem = Servant.get();
    }

    public int getElem() {
        return elem;
    }
}
