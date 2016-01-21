package Test.MultilThread.produce;

/**
 * Created by Amysue on 2016/1/21.
 */
public class Customer implements Runnable {
    private String name;
    private Disk   disk;

    public Customer(String name, Disk disk) {
        this.name = name;
        this.disk = disk;
    }

    public void eat() {
        synchronized (disk) {
            if (!disk.isEmpty()) {
                String f = disk.getFood();
                System.out.println("Customer " + name + " is enjoying: " + f);
                disk.setEmpty(true);
                disk.notify();
                try {
                    disk.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    disk.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            eat();
            if (disk.isEnd()) {
                break;
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
