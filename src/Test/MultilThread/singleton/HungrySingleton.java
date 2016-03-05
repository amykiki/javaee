package Test.MultilThread.singleton;

/**
 * Created by Amysue on 2016/3/5.
 */
public class HungrySingleton{
    private HungrySingleton() {
        System.out.println("Hungry Singleton is creating");
    }

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

}
