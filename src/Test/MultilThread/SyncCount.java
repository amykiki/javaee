package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/21.
 */
public class SyncCount implements Runnable {
    private int index;

    public SyncCount() {
        index = 0;
    }

    @Override
    public void run() {
        count();
    }

//    public synchronized void count() {
//        for (int i = 0; i < 10; i++) {
//            int tmp = 0;
//            tmp = index + 1;
//            System.out.println(Thread.currentThread().getName() + "_tmp:" + tmp);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            index = tmp;
//            System.out.println(Thread.currentThread().getName() + "_index:" + index);
//
//        }
//    }

    public void count() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                int tmp = 0;
                tmp = index + 1;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                index = tmp;
                System.out.println(Thread.currentThread().getName() + "_index:" + index);
            }

        }
    }
}
