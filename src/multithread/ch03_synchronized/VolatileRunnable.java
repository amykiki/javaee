package multithread.ch03_synchronized;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  multithread.ch03_synchronized
 * @Description :  测试volatile关键字是否能保证线程同步。
 *                 参考https://www.cnblogs.com/Think-007/p/7084375.html
 *                 http://www.cs.umd.edu/~pugh/java/memoryModel/issues.pdf
 * @Creation Date:  2018-04-20 18:17
 * --------  ---------  --------------------------
 */
public class VolatileRunnable implements Runnable{
    private volatile int a = 0;
    @Override
    public void run() {
//        synchronized (this) {
            a = a + 1;
            System.out.println(Thread.currentThread().getName() + ":----" + a);
            try {
                Thread.sleep(100);
                a = a + 2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":----" + a);
//        }
    }
}
