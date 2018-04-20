package multithread.ch03_synchronized;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  multithread.ch03_synchronized
 * @Description :  测试volatile关键字是否能保证线程同步。参考https://www.cnblogs.com/Think-007/p/7084375.html
 * @Creation Date:  2018-04-20 18:37
 * --------  ---------  --------------------------
 */
public class VolatileMain {
    public static Foo GF;
    public static void testVolatile1() {
        VolatileRunnable vr = new VolatileRunnable();

        Thread t0 = new Thread(vr);
        Thread t1 = new Thread(vr);
        Thread t2 = new Thread(vr);
        Thread t3 = new Thread(vr);

        t0.start();
        t1.start();
        t2.start();
        t3.start();

//        With synchronized
//        Thread-3:----1
//        Thread-3:----3
//        Thread-1:----4
//        Thread-1:----6
//        Thread-0:----7
//        Thread-0:----9
//        Thread-2:----10
//        Thread-2:----12

//        Without synchronized
//        Thread-0:----1
//        Thread-2:----2
//        Thread-1:----3
//        Thread-3:----4
//        Thread-0:----8
//        Thread-1:----10
//        Thread-3:----8
//        Thread-2:----8
    }

    public static void test2() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Foo f = new Foo();
                f.field = "Test";
                GF = f;
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Foo r1 = GF;
                if (r1 != null) {
                    String b1 = r1.field;
                    System.out.println(b1);
                }
            }
        });

        t1.run();
        t2.run();
    }
    public static void main(String[] args) {
        test2();

    }
}
