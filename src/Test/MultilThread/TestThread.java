package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/20.
 */
public class TestThread {
    public static void main(String[] args) {
        FirstThread ft = new FirstThread("Amy");
        ft.start();

        RunThread rt = new RunThread("Star");
        rt.begin();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
        System.out.println("=====================Thread ended=====================");
//        MoveBall mb = new MoveBall(800, 600);
    }
}
