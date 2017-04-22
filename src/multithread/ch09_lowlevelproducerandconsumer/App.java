package multithread.ch09_lowlevelproducerandconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zoush on 2017/4/22.
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Processor processor = new Processor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                processor.producer();
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                processor.consumer();
            }
        });

        executor.shutdown();
        System.out.println("Producer and Consumer START!!");

        boolean allFinished = true;
        try {
            allFinished = executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("AllFinished " + allFinished);
        if (!allFinished) {
            System.out.println("Threads NOT STOPPED");
            executor.shutdownNow();
        }
    }
}
