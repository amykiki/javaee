package interview;

public class Singleton {
    private static class SingletonHelper {
        static Singleton instance = new Singleton();
    }

    private Singleton(){
    }

    public static Singleton getInstance() {
        return SingletonHelper.instance;
    }
}
