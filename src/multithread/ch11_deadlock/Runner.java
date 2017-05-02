package multithread.ch11_deadlock;

import multithread.ExecutoreApp;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zoush on 2017/5/2.
 */
public class Runner {
    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
        while (true) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if(gotFirstLock && gotSecondLock) return;
                else if(gotFirstLock) firstLock.unlock();
                else if(gotSecondLock) secondLock.unlock();
            }
            Thread.sleep(1);
        }
    }
    public void firstThread() throws InterruptedException{
        System.out.println("Thread1 START");
        Random random = new Random();
        for(int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException{
        System.out.println("Thread2 START");
        Random random = new Random();
        for(int i = 0; i < 10000; i++) {
            acquireLocks(lock2, lock1);
            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }
    }

    public void finished() {
        System.out.println("Account1 banlance: " + acc1.getBalance());
        System.out.println("Account2 banlance: " + acc2.getBalance());
        System.out.println("Total banlance: " + (acc1.getBalance() + acc2.getBalance()));
    }

    public static void main(String[] args) {
        final Runner runner = new Runner();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutoreApp app = new ExecutoreApp(2, true, 1);
//        ExecutoreApp app = new ExecutoreApp(2);
        app.runApp(runnable1, runnable2);
        runner.finished();

    }
}
