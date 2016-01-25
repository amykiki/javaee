package Test.MultilThread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Amysue on 2016/1/24.
 */
public class SimpleThreadPool {
    public void test() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + (i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Finish all Threads");

    }
}
