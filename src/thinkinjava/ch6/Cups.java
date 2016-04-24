package thinkinjava.ch6;

/**
 * Created by Amysue on 2016/4/24.
 */
public class Cups {
    public static Cup cup1;
    private Cup cup2;

    static {
        cup1 = new Cup(1);
        cup1.f(1);
    }
    {
        cup2 = new Cup(2);
        cup2.f(2);
    }

    public Cups() {
        System.out.println("Cups()");
    }
}
