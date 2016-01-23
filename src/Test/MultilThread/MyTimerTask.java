package Test.MultilThread;


import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Amysue on 2016/1/23.
 */
public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("Task start at: " + new Date());
        completTask();
        System.out.println("Task end at: " + new Date());

    }

    private void completTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
