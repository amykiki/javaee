package effectivejava;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  effectivejava
 * @Description :
 * @Creation Date:  2018-07-18 14:32
 * --------  ---------  --------------------------
 */
public class Item5AutoBox {
    public static void test1() {
        long timestart = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long timeend = System.currentTimeMillis();
        System.out.println("timestart = " + timestart);
        System.out.println("TEST1 spend time = " + (timeend - timestart));
        System.out.println("timeend = " + timeend);
        System.out.println(sum);
    }

    public static void test2() {
        long timestart = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long timeend = System.currentTimeMillis();
        System.out.println("timestart = " + timestart);
        System.out.println("TEST2 spend time = " + (timeend - timestart));
        System.out.println("timeend = " + timeend);
        System.out.println(sum);
    }

    public static void main(String[] args) {
        System.out.println("======Test test1 With LONG BEGINE=====");
        test1();
        System.out.println("======Test test1 With LONG END=====");
        System.out.println("======Test test2 With long BEGINE=====");
        test2();
        System.out.println("======Test test2 With long END=====");
    }
}
