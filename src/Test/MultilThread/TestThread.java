package Test.MultilThread;

import Test.MultilThread.Alarm.Clock;
import Test.MultilThread.Alarm.SleepMan;
import Test.MultilThread.ProduceQueue.Cooker;
import Test.MultilThread.ProduceQueue.Customer;
import Test.MultilThread.ProduceQueue.Disk;
//import Test.MultilThread.produce.Cooker;
//import Test.MultilThread.produce.Customer;
//import Test.MultilThread.produce.Disk;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Amysue on 2016/1/20.
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
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

//        DeadLock dl = new DeadLock();
//        new Thread(dl, "Amy").start();
//        new Thread(dl, "Kevin").start();

        //===========================Producer & Consumer====================================
//        Disk d = new Disk();
//        Cooker co = new Cooker("Amy", d);
//        Customer cu = new Customer("Kevin", d);
//        Thread cot = new Thread(co);
//        Thread cut = new Thread(cu);
//        cut.setDaemon(true);
//        cot.start();
//        cut.start();

        //===========================Alarm====================================
//        Clock c = new Clock();
//        SleepMan man = new SleepMan("Amy", c);
//        Thread ct = new Thread(c);
//        Thread mant = new Thread(man);
//        mant.setDaemon(true);
//        ct.start();
//        mant.start();

        //===========================Join Test====================================
//        MyRunnable run = new MyRunnable();
//        Thread t1 = new Thread(run, "t1");
//        Thread t2 = new Thread(run, "t2");
//        Thread t3 = new Thread(run, "t3");
//        synchronized (MyRunnable.class) {
//            System.out.println("hacked");
//            int i = 1;
//            while (i < 6) {
//                Thread.sleep(1000);
//                System.out.println("sleep " + i + " seconds");
//                i++;
//            }
//            System.out.println("hack end");
//        }
//
//        t1.start();
//
////        t1.join(2000);
//        t2.start();
//
////        t1.join();
//        t3.start();
//
//        t1.join();
//        t2.join();
//        t3.join();
//
//        System.out.println("All thread are dead, exiting main thread");

        //===========================ProcessThread====================================
//        ProcessThread pt = new ProcessThread();
//        Thread t1 = new Thread(pt, "t1");
//        t1.start();
//        Thread t2 = new Thread(pt, "t2");
//        t2.start();
//
//        t1.join();
//        t2.join();
//        System.out.println("Process Thread Count=" + pt.getCount());

        //===========================MyTimerTask Test====================================
//        TimerTask tt = new MyTimerTask();
//        Timer timer = new Timer(true);
//        timer.scheduleAtFixedRate(tt, 0, 1000);
//        System.out.println("Timer task start");
//        Thread.sleep(12000);
//        timer.cancel();
//        System.out.println("Timer task cancled");
//        Thread.sleep(100);

        //===========================ProduceQueue Test====================================
        Disk d = new Disk(5);
//        d.setFood("1");
//        d.setFood("2");
//        d.setFood("3");
//        d.setFood("4");
//        d.setFood("5");
//        d.setFood("6");
        Cooker co = new Cooker("Amy", d, 4000);
        Customer cu = new Customer("Kevin", d, 2000);

        new Thread(co).start();
        Thread.sleep(8000);
        new Thread(cu).start();

    }


}
