package Test.MultilThread.singleton;

/**
 * Created by Amysue on 2016/3/5.
 */
public class HolderSingleton {
    private HolderSingleton() {
        System.out.println("Holder Singleton is creating");
    }
    private static class SingletonHolder {
        private static final HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
