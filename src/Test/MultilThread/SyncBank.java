package Test.MultilThread;

/**
 * Created by Amysue on 2016/1/21.
 */
public class SyncBank implements Runnable {
    private int saveMoney;
    private int getMoney;
    private int curMoney;
    private int times;

    public SyncBank() {
        saveMoney = 5000;
        getMoney = 2000;
        curMoney = 0;
        times = 0;
    }

    public void test() {
        Thread td1 = new Thread(this, "Husband");
        Thread td2 = new Thread(this, "Wife");
        td1.start();
        td2.start();
        while (true) {
            if (this.times > 0) {
                this.showMoney();
                break;
            }
        }
    }

    public int getTimes() {
        return times;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " get " + getMoney + "RMB");
        this.getMoney();
    }

    public synchronized void getMoney() {
        curMoney += getMoney;
        int tmp = saveMoney - getMoney;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveMoney = tmp;
        times++;
        System.out.println("times = " + times);

    }


    public void showMoney() {
        System.out.println("saveMoney = " + saveMoney + ", curMoney = " + curMoney);
    }
}
