package multithread.ch10_reentrantlock;

/**
 * Created by zoush on 2017/4/29.
 */
public class App {
    public static void main(String[] args) throws InterruptedException{
        final Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException ignored) {
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException ignored) {
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        runner.finished();
    }
}
