package multithread.ch13_CallableAndFuture;

import java.util.concurrent.*;

/**
 * Created by zoush on 2017/5/2.
 */
class CallableImpl implements Callable<Integer> {
    private int myName;

    public CallableImpl(int myName) {
        this.myName = myName;
    }

    public int getMyName() {
        return myName;
    }

    @Override
    public Integer call() throws Exception {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread: " + getMyName() + " value is: " + i);
        }
        return getMyName();
    }
}
public class CallableTester {
    public static void main(String[] args) throws InterruptedException{
        Callable<Integer> callable = new CallableImpl(2);
        ExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        Future<Integer> future = executorService.submit(callable);

        try {
            System.out.println("Future value: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Executor Shutdown...");
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("executorService.isShutdown() = " + executorService.isShutdown());
    }
}
