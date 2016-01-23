package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/23.
 */
public class ProcessThread implements Runnable {
    private int count;
    private Object key = new Object();

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            processSomething(i);
            synchronized (key) {
                count++;
            }
        }
    }

    public int getCount() {
        return count;
    }

    private void processSomething(int i) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
