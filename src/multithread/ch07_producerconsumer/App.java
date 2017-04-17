package multithread.ch07_producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zoush on 2017/4/17.
 */
public class App {
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) {
        App app = new App();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                app.producer();
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                app.consumer();
            }
        });
        executor.shutdown();
    }

    private void producer() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = random.nextInt(100);
            boolean added = queue.offer(value);
            if (added) {
                System.out.println("Offer " + value + " into blocking queue , queue size is " + queue.size());
            }
        }
    }

    private void consumer() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (random.nextInt(10) == 0) {
                int value = queue.poll();
                System.out.println("remove value " + value + ", queue size is " + queue.size());
            }
        }
    }
}
