package Test.MultilThread.Alarm;

/**
 * Created by Amysue on 2016/1/22.
 */
public class Clock implements Runnable {
    private boolean alarmed;

    public Clock() {
        alarmed = false;
    }

    public boolean isAlramed() {
        return alarmed;
    }

    public void setAlarmed(boolean alarmed) {
        this.alarmed = alarmed;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                if (!alarmed) {
                    System.out.println((i+1) + " Clock:Dang...Dang...Dang...It's time to get up");
                    alarmed = true;
                    this.notify();
                    System.out.println("lalalala ");
                    try {
                        this.wait();
                        System.out.println(i + "-sleeping.....");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
