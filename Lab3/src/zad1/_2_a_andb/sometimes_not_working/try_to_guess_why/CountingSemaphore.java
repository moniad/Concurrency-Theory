package zad1._2_a_andb.sometimes_not_working.try_to_guess_why;

public class CountingSemaphore {
    private int resourceCount;
//    private BinarySemaphore resourceSemaphore;
    private BinarySemaphore accessSemaphore;

    public CountingSemaphore(int resourceCount) {
        this.resourceCount = resourceCount;
        accessSemaphore = new BinarySemaphore(true);
//        resourceSemaphore = new BinarySemaphore(false);
    }

    public synchronized void P() {
//        accessSemaphore.P();
        while (resourceCount <= 0) {
//            resourceSemaphore.P();
            try {
//                accessSemaphore.V();
                wait();
//                accessSemaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("resource: " + resourceCount);
        resourceCount--;
//        accessSemaphore.V();
    }

    public synchronized void V() {
//        accessSemaphore.P();
//        if (resourceCount > 0) {
//            resourceSemaphore.V();
//        }
        resourceCount++;
        this.notify();
        System.out.println("resource: " + resourceCount);
//        accessSemaphore.V();
    }

    public int getResourceCount() {
        return resourceCount;
    }
}
