package multithread.ch06_countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  multithread.ch06_countdownlatch
 * @Description :
 * @Creation Date:  2018-09-05 11:43
 * --------  ---------  --------------------------
 */
public class InitialTest {
    public static void main(String[] args) {
        final CountDownLatch startGate = new CountDownLatch(1);
        int threadNum = 10;
        for(int i = 0; i < threadNum; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        AES.initialize();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }

        startGate.countDown();
    }
}