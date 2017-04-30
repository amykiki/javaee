package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zoush on 2017/4/29.
 */
public class ExecutoreApp {
    private int count;
    private boolean timeoutShutdown = false;
    private int timeout;
    private ExecutorService executorService;

    public ExecutoreApp(int count) {
        this.count = count;
    }

    public ExecutoreApp(int count, boolean timeoutShutdown, int timeout) {
        this.count = count;
        this.timeoutShutdown = timeoutShutdown;
        this.timeout = timeout;
    }

    public void runApp(Runnable...runnables) {
        executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < runnables.length; i++) {
            executorService.submit(runnables[i]);
        }
        executorService.shutdown();

        if (timeoutShutdown) {
            boolean allFinished = true;
            try {
                allFinished = executorService.awaitTermination(timeout, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AllFinished is " + allFinished);
            if (!allFinished) {
                System.out.println("Terminate Threads now!");
                executorService.shutdownNow();
            }
        }
    }
}
