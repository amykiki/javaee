package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/21.
 */
public class DeadLock implements Runnable {
    private Object key1 = new Object();
    private Object key2 = new Object();
    private boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            flag = false;
            synchronized (key1) {
                System.out.println(Thread.currentThread().getName() + ":k1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (key2) {
                    System.out.println(Thread.currentThread().getName() + ":k2");
                }
            }
        } else {
            flag = true;
            synchronized (key2) {
                System.out.println(Thread.currentThread().getName() + ":k2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (key1) {
                    System.out.println(Thread.currentThread().getName() + ":k1");
                }
            }
        }
    }
}
