package Test.MultilThread.Alarm;

/**
 * Created by Amysue on 2016/1/22.
 */
public class SleepMan implements Runnable {
    private String name;
    private Clock  c;

    public SleepMan(String name, Clock c) {
        this.name = name;
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (c) {
                if (c.isAlramed()) {
                    c.setAlarmed(false);
                    System.out.println(name + " said I know!");
                    c.notify();
                    System.out.println("clock could ringing...");
                    try {
                        c.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        c.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
