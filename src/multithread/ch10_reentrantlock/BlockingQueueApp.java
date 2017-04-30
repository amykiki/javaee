package multithread.ch10_reentrantlock;

import multithread.ExecutoreApp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zoush on 2017/4/29.
 */
class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<T>();
    private int capacity;
    private Lock lock = new ReentrantLock();
    //condition variables
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("Queue is full cannot put");
                notFull.await(); //release lock, wait notfull signal
            }
            queue.add(element);
            System.out.println("Added to the queue " + element);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException{
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty, cannot take");
                notEmpty.await(); //release lock, wait notempty signal
            }

            T item = queue.remove();
            System.out.println("Removed to the queue" + item);
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
public class BlockingQueueApp {
    public static void main(String[] args) {
        final BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(10);
        final Random random = new Random();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        blockingQueue.put(random.nextInt(10));
                    }
                } catch (InterruptedException e) {
                    System.out.println("Producer Thread is Interrupted");
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    while (true) {
                        blockingQueue.take();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Consumer Thread is Interrupted");
                }
            }
        };

        ExecutoreApp executoreApp = new ExecutoreApp(2, true, 2);
        executoreApp.runApp(runnable1, runnable2);

    }
}
