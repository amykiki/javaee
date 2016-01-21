package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/20.
 */
public class FirstThread extends Thread {
    public FirstThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + ": " + i);
        }
    }
}
