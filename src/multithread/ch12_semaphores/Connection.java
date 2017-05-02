package multithread.ch12_semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by zoush on 2017/5/2.
 */
public class Connection {
    private Connection() {

    }

    private Semaphore sem = new Semaphore(10, true);
    private static Connection instance = new Connection();
    private int connections = 0;

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {

        try {
            sem.acquire();
            doConnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    private void doConnect() {
        synchronized (this) {
            //原子操作 Semaphore类只是资源数量的抽象表示，并不负责管理资源对象本身。
            // 可能有多个线程同时获取到资源使用许可，因此需要使用同步机制避免资源竞争
            connections++; //模拟获取资源的操作
            System.out.printf("%s:: Current connections (max 10 allowed): %d\n", Thread.currentThread().getName(), connections);
        }

        try {
            System.out.printf("%s:: WORKING...\n", Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) { //原子操作
            connections--;
            System.out.printf("I'm done. %s:: Connection released. Permits Left = %d\n", Thread.currentThread().getName(), connections);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 20; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
