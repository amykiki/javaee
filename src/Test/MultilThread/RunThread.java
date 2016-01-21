package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/20.
 */
public class RunThread implements Runnable{
    private String name;
    private int index;
    private boolean flag = true;

    public RunThread(String name) {
        this.name = name;
    }

    public RunThread() {
        this.name = "Runnable";
    }

    public void begin() {
        Thread td = new Thread(this, name);
        td.start();
    }
    @Override
    public void run() {
        for (; index < 2000; index++) {
            if (!flag) break;
            System.out.println("--------------------------" + Thread.currentThread().getName() + ": " + index);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public int getIndex() {
        System.out.println("index = " + index);
        return index;
    }

    public void stopThread() {
        flag = false;
    }
}
