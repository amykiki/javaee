package multithread.ch10_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  multithread.ch10_reentrantlock
 * @Description :
 * @Creation Date:  2018-09-21 18:55
 * --------  ---------  --------------------------
 */
public class ParkTest {
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1 is running");
                /*try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("Park t1");
                LockSupport.park();
                System.out.println("T1 runs again!!");
            }
        });

        t1.start();
        LockSupport.unpark(t1);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("2 seconds has passed");
        System.out.println("Unpark t1");
        LockSupport.unpark(t1);
    }
}
