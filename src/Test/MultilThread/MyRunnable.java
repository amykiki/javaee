package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/22.
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread started:::" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended===" + Thread.currentThread().getName());
    }
}
