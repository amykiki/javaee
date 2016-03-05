package Test.MultilThread.singleton;

/**
 * Created by Amysue on 2016/3/5.
 */
public class LazySingletonNotSafe{
    private LazySingletonNotSafe() {
        System.out.println("LazySingletonNotSafe is creating");
    }

    private static LazySingletonNotSafe instance = null;

    public static LazySingletonNotSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonNotSafe();
        }
        return instance;
    }

}
