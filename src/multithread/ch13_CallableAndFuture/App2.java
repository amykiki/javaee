package multithread.ch13_CallableAndFuture;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by zoush on 2017/5/2.
 */

class MyCallable implements Callable<Integer> {
    private int value;

    public MyCallable(int value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Begin Call...");
        Integer sum = 0;
        for(int i = 0; i < value; i++) {
            sum += i;
        }
        System.out.println("Begin Sleeping...");
        Thread.sleep(1000);
        System.out.println("Sum in Callable.Call() of " + Thread.currentThread().getName() + ":value = " + value + ", sum = " + sum);
        return sum;
    }
}
public class App2 {
    public static void main(String[] args) throws InterruptedException{
        ArrayList<Future<Integer>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++) {
            Future<Integer> future = executorService.submit(new MyCallable(i));
            list.add(future);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        for(int i = 0; i < list.size(); i++) {
            try {
                System.out.println("List Values " + i + " Value: " + list.get(i).get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
