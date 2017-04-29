package multithread.ch10_reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zoush on 2017/4/29.
 */
public class Runner {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException{
        lock.lock();
        System.out.println("FirstThread Waiting...");
        cond.await();
        System.out.println("FirstThread Woken up!");
        try {
            System.out.println("FirstThread Get The Lock");
            increment();
            System.out.print("FirstThread Finished Increment ");
            finished();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!, wake other Thread");
        cond.signal();
        try {
            System.out.println("SecondThread Get The Lock");
            increment();
            System.out.print("SecondThread Finished Increment ");
            finished();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
