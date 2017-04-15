package thinkinjava.ch7.local;

/**
 * Created by Amysue on 2016/4/25.
 */
class Packaged {
    public static Packaged p1 = new Packaged();
    public Packaged() {
        System.out.println("Create a packaged Class");
    }

    public static Packaged getP1() {
        return p1;
    }

    public void f() {
        System.out.println("Calling f()");
    }

}
