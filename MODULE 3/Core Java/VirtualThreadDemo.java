public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        int totalThreads = 100_000;

        long startVirtual = System.currentTimeMillis();
        for (int i = 0; i < totalThreads; i++) {
            Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread: " + Thread.currentThread());
            });
        }
        long endVirtual = System.currentTimeMillis();
        System.out.println("Virtual threads launched in: " + (endVirtual - startVirtual) + " ms");

        long startPlatform = System.currentTimeMillis();
        Thread[] threads = new Thread[totalThreads];
        for (int i = 0; i < totalThreads; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("Hello from platform thread: " + Thread.currentThread());
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long endPlatform = System.currentTimeMillis();
        System.out.println("Platform threads launched in: " + (endPlatform - startPlatform) + " ms");
    }
}
