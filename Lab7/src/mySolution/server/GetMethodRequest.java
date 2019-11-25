package mySolution.server;

import mySolution.utils.CustomFuture;

public class GetMethodRequest extends MethodRequest {
    private CustomFuture customFuture;

    public GetMethodRequest(CustomFuture customFuture) {
        this.customFuture = customFuture;
    }

    @Override
    public boolean guard() {
        return !Servant.isBufferEmpty();
    }

    @Override
    public void call() {
        customFuture.setObject(Servant.get());
    }
}
