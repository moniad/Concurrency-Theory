package server;

public class Scheduler extends Thread {
    private int tasksToBeRemovedLast;
    private boolean hasStatusChanged;
    private ActivationQueue activationQueue;

    public Scheduler() {
        this.tasksToBeRemovedLast = 0;
        this.hasStatusChanged = false;
        activationQueue = new ActivationQueue();
    }

    @Override
    public void run() {
        try {
            dispatch();
        } catch (InterruptedException e) {
            System.out.println("Scheduler: dispatch()");
        }
    }

    private void dispatch() throws InterruptedException {
        MethodRequest methodRequest;
        while (true) {
            if (tasksToBeRemovedLast > 0 && hasStatusChanged) {
                methodRequest = activationQueue.dequeueLast();
                tasksToBeRemovedLast--;
            } else {
                methodRequest = activationQueue.dequeueFirst();
                if (methodRequest != null) {
                    hasStatusChanged = true;
                } else {
                    continue;
                }
            }

            if (methodRequest.guard()) {
                methodRequest.call();
            } else {
                // when task cannot be executed, it is put into queue and will be removed as last
                System.out.println("CANNOT EXECUTE TASK");
                activationQueue.enqueue(methodRequest);
                tasksToBeRemovedLast++;
                hasStatusChanged = false;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    public void enqueue(MethodRequest methodRequest) throws InterruptedException {
        activationQueue.enqueue(methodRequest);
    }
}
