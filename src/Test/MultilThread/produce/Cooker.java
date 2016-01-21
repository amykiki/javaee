package Test.MultilThread.produce;

import java.util.Random;

/**
 * Created by Amysue on 2016/1/21.
 */
public class Cooker implements Runnable {
    private String   name;
    private String[] foods;
    private Disk     disk;
    private Random ran = new Random();

    public Cooker(String name, Disk disk) {
        this.name = name;
        this.disk = disk;
        foods = new String[]{"rice", "noddle", "vegetable", "chicken", "pizza", "hamburg",
                             "pudding", "sandwich", "sausage", "wine", "potato"};
    }

    public void make() {
        synchronized (disk) {
            if (disk.isEmpty()) {
                int    index = ran.nextInt(foods.length);
                String f     = foods[index];
                System.out.println("Cooker " + name + " has maked: " + f);
                disk.setFood(f);
                disk.setEmpty(false);
                disk.notify();
                try {
                    disk.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
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
        for (int i = 0; i < 20; i++) {
//            System.out.println(i);
            make();
        }
        disk.setEnd(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFoods() {
        return foods;
    }

    public void setFoods(String[] foods) {
        this.foods = foods;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
