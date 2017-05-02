package multithread.ch14_interrupt;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zoush on 2017/5/2.
 */
public class App {
    public static void main(String[] args) throws InterruptedException{
        long starttime = System.currentTimeMillis();
        System.out.println("Starting..." + starttime);
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Random random = new Random();
                for(int i = 0; i < 1E8; i++) {
                    Math.sin(random.nextDouble());
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.printf("Interrupted at %d !!!\n", i);
                        break;
                    }
                }
                return null;
            }
        });

//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                Random random = new Random();
//                for(int i = 0; i < 1E8; i++) {
//                    Math.sin(random.nextDouble());
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.printf("Interrupted at %d !!!\n", i);
//                        break;
//                    }
//                }
//            }
//        });

        executor.shutdown();
        Thread.sleep(500);
        //executor.shutdownNow或者future.cancel(true)都能中断线程
//        executor.shutdownNow();
        future.cancel(true); //为true时能中断线程，false不能
//        future.cancel(false);

        boolean allFinished = executor.awaitTermination(1, TimeUnit.HOURS);
        long endTime = System.currentTimeMillis();
        System.out.println("allFinished = " + allFinished + ", time " + endTime);
        System.out.println("Duration: " + (endTime - starttime));
    }
}
