package Test.MultilThread.singleton;

/**
 * Created by Amysue on 2016/3/5.
 */
public class LazySingletonSafe {
    private LazySingletonSafe() {
        System.out.println("LazySingleton Thread Safe is creating");
    }
    private volatile static LazySingletonSafe instance = null;

    public static LazySingletonSafe getInstance() {
        if (instance == null) {
            synchronized (LazySingletonSafe.class) {
                if (instance == null) {
                    instance = new LazySingletonSafe();
                }
            }
        }
        return instance;
    }
}
