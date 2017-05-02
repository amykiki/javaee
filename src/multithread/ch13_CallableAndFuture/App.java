package multithread.ch13_CallableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zoush on 2017/5/2.
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if (duration > 2000) {
                    throw new TimeoutException("Sleeping for " + duration + " too long!");
                }
                System.out.println("Starting...");
                try {
                    Thread.sleep(duration);
                    System.out.println("Sleep duration: " + duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished");
                return duration;
            }
        });
        executor.shutdown();
        try {
            System.out.println("future result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
//            System.out.println(e.getMessage());
            TimeoutException ex = (TimeoutException) e.getCause();
            System.out.println("======" + ex.getMessage() + "======");
        }
    }
}
