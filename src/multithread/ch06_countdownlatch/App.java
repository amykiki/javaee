package multithread.ch06_countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zoush on 2017/4/17.
 */
class Processor implements Runnable {
    private CountDownLatch latch;
    private int id;

    public Processor(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Start ID:" + id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("Latch Countdown by One");

    }
}
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(5);
        for(int i = 0; i < 6; i++) {
            executorService.submit(new Processor(latch, i));
        }
        System.out.println("Starting Tasks....");
        executorService.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All task complete");
    }
}
