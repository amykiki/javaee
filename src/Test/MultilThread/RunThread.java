package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/20.
 */
public class RunThread implements Runnable{
    private String name;

    public RunThread(String name) {
        this.name = name;
    }

    public void begin() {
        Thread td = new Thread(this, name);
        td.start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
