package Test.MultilThread.ProduceQueue;

/**
 * Created by Amysue on 2016/1/21.
 */
public class Customer implements Runnable {
    private String name;
    private Disk   disk;
    private int sleepTime;

    public Customer(String name, Disk disk, int time) {
        this.name = name;
        this.disk = disk;
        this.sleepTime = time;
    }

    public void eat(String food) {
        System.out.println("Customer " + name + " is enjoying: " + food);
        System.out.println();
        System.out.println();
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String curFood = null;
        while ((curFood = disk.getFood()) != "exit") {
            if (curFood != null) {
                eat(curFood);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
