package multithread.ch06_countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author :  ZouShumin
 * @Project Name :  passport-service
 * @Package Name :  com.yihaodian.front.passport.server.utils.wechatmina
 * @Description :
 * @Creation Date:  2018-09-04 17:19
 * --------  ---------  --------------------------
 */
public class AES {
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static CountDownLatch initializedLatch = new CountDownLatch(1);

    public byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
        return null;
    }

    public static void initialize() {
        if (initialized.compareAndSet(false, true)) {
            System.out.println(Thread.currentThread().getName() + " is set initialized to true!!");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initializedLatch.countDown();
        } else {
            String threadName = Thread.currentThread().getName();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println( threadName + " the initialized value is already true!" + initializedLatch.toString());
            long start = System.nanoTime();
//            long latchCount = initializedLatch.getCount();
            try {
                initializedLatch.await();
            } catch (InterruptedException e) {
            }
            long end = System.nanoTime();

            System.out.println(threadName + " cost " + (end - start));

        }
    }

}
