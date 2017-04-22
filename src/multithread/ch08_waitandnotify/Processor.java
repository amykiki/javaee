package multithread.ch08_waitandnotify;

import java.util.Scanner;

/**
 * Created by zoush on 2017/4/22.
 */
public class Processor {
    public void produce() throws InterruptedException{
        synchronized (this) {
            System.out.println("Producer thread running...");
            wait();
            System.out.println("Producer thread resumed");
        }
    }

    public void consume() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Consumer waiting for returnKey...");
            scanner.nextLine();
            System.out.println("Consumer resturnKey pressed");
            //注意notify只是会唤醒wait的线程，但是不会释放lock，lock只有在当前线程结束后释放
            // 所以Consumer Done!!打印后Producer thread resumed 才会被打印
            notify();
            Thread.sleep(5000);
            System.out.println("Consumer Done!!");
        }

    }
}
