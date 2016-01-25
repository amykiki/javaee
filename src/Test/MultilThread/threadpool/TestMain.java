package Test.MultilThread.threadpool;

import java.util.concurrent.*;

/**
 * Created by Amysue on 2016/1/24.
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
//        SimpleThreadPool stp1 = new SimpleThreadPool();
//        stp1.test();
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), threadFactory, rejectionHandler);

        MyMonitorThread monitor = new MyMonitorThread(executorPool, 2);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        for (int i = 0; i < 10; i++) {
            executorPool.execute(new WorkerThread("" + (i + 1)));
        }

        Thread.sleep(15000);
        executorPool.shutdown();
        Thread.sleep(5000);
        monitor.shutdown();
    }

}
