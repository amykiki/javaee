package multithread.ch10_reentrantlock;

import java.lang.annotation.Target;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  multithread.ch10_reentrantlock
 * @Description :
 * @Creation Date:  2018-09-10 18:35
 * --------  ---------  --------------------------
 */
public class MainTest {
    private static class RunJob implements Runnable {
        private String jobName;
        private Lock lock;
        public RunJob(String jobName, Lock lock) {
            this.jobName = jobName;
            this.lock = lock;
        }
        @Override
        public void run() {
            System.out.println(jobName + " is running...");
            lock.lock();
            try {
                System.out.println(jobName + " Get Lock!!!");
            } finally {
                lock.unlock();
                System.out.println(jobName + " release Lock!!");
            }
        }
    }
    public static void main(String[] args) throws Exception{
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("T1 is running....");
                lock.lock();
                try {
                    System.out.println("T1 Get Lock!!");
                    Thread.sleep(50000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("T1 release Lock!!");
                }
            }
        };

        t1.start();
        Thread.sleep(100);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(new RunJob("T2", lock));
        exec.submit(new RunJob("T3", lock));
        exec.shutdown();

    }
}
