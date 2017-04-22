package multithread.ch09_lowlevelproducerandconsumer;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by zoush on 2017/4/22.
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void producer(){
        int value = 0;
        boolean shutdown = false;
        while (!shutdown) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Producer received SHUTDOWN COMMAND");
                        shutdown = true;
                        break;
                    }
                }
                list.add(value);
                System.out.println("Producer added: " + value);
                value++;
                lock.notify();
            }
        }
    }

    public void consumer(){
        Random random = new Random();
        int value = 0;
        boolean shutdown = false;
        while (!shutdown) {
            synchronized (lock) {
                while (list.size() == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Consumer received SHUTDOWN COMMAND");
                        shutdown = true;
                        break;
                    }
                }
                value = list.removeFirst();
                System.out.print("Removed value by Consumer is: " + value);
                System.out.println(" Now list size is " + list.size());
                lock.notify();
            }
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                System.out.println("Consumer received SHUTDOWN COMMAND");
                shutdown = true;
            }
            System.out.println("Consumer finished " + value + "!!!");
        }
    }
}
