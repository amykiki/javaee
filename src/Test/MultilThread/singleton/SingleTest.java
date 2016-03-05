package Test.MultilThread.singleton;

/**
 * Created by Amysue on 2016/3/5.
 */
public class SingleTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new RunSingleton()).start();
        }
    }
}
