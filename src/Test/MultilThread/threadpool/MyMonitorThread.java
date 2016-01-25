package Test.MultilThread.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Amysue on 2016/1/24.
 */
public class MyMonitorThread implements Runnable {
    private ThreadPoolExecutor executor;
    private int delay;
    private boolean run = true;

    public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.delay = delay;
    }

    public void shutdown() {
        run = false;
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Complete: %d, Task: %d, isShutDown: %s, isTerminated: %s",
                            this.executor.getPoolSize(),
                            this.executor.getCorePoolSize(),
                            this.executor.getActiveCount(),
                            this.executor.getCompletedTaskCount(),
                            this.executor.getTaskCount(),
                            this.executor.isShutdown(),
                            this.executor.isTerminated())
            );
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
