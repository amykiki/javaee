package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/20.
 */
public class TestThread {
    public static void main(String[] args) {
//        FirstThread ft = new FirstThread("Amy");
//        ft.start();

//        RunThread rt = new RunThread("Star");
//        rt.begin();
//        while (true) {
//            if (rt.getIndex() >= 1000) {
//                System.out.println("================END============================");
//                rt.stopThread();
//                break;
//            }
//        }
//        RunThread rt1 = new RunThread();
//        RunThread rt2 = new RunThread();
//        Thread td1 = new Thread(rt1, "first");
//        Thread td2 = new Thread(rt2, "second");
//        td1.start();
//        td2.start();
//        for (int i = 0; i < 100; i++) {
//            System.out.println(Thread.currentThread().getName() + ": " + i);
//        }
//        System.out.println("=====================Thread ended=====================");
//        MoveBall mb = new MoveBall(1200, 600);

        //===========================SyncBank====================================
//        SyncBank sb = new SyncBank();
//        Thread td1 = new Thread(sb, "Husband");
//        Thread td2 = new Thread(sb, "Wife");
//        td1.start();
//        td2.start();
//        while (true) {
//            int i = sb.getTimes();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (i >= 2) {
//                sb.showMoney();
//                break;
//            }
//        }
        //===========================SyncBank====================================


        //===========================SyncCount====================================
//        SyncCount sc = new SyncCount();
//        Thread tdsc1 = new Thread(sc, "Amy");
//        Thread tdsc2 = new Thread(sc, "Kevin");
//        tdsc1.start();
//        tdsc2.start();

        //===========================DeadLock====================================
        DeadLock dl = new DeadLock();
        new Thread(dl, "Amy").start();
        new Thread(dl, "Kevin").start();
    }

}
