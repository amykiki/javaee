package Test.MultilThread.singleton;

/**
 * Created by Amysue on 2016/3/5.
 */
public class RunSingleton implements Runnable {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
//            HungrySingleton.getInstance();
//            LazySingletonNotSafe.getInstance();
            LazySingletonSafe.getInstance();
//            HolderSingleton.getInstance();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }
}
