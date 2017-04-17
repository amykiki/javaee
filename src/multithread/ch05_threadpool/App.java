package multithread.ch05_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zoush on 2017/4/17.
 */
class Processor implements Runnable {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting ID: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed ID:" + id);
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5; i++) {
            executor.submit(new Processor(i));
        }

        executor.shutdown();
        System.out.println("All task submitted");
        long start = System.currentTimeMillis();
        boolean allCompleted = true;
        try {
            allCompleted = executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("All task completed: " + allCompleted + ", durationg: " + (end - start));

    }
}
