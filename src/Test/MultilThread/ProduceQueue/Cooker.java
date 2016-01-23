package Test.MultilThread.ProduceQueue;

import java.util.Random;

/**
 * Created by Amysue on 2016/1/21.
 */
public class Cooker implements Runnable {
    private String   name;
    private String[] foods;
    private Disk     disk;
    private int sleepTime;
    private Random ran = new Random();


    public Cooker(String name, Disk disk, int time) {
        this.name = name;
        this.disk = disk;
        this.sleepTime = time;
        foods = new String[]{"rice", "noddle", "vegetable", "chicken", "pizza", "hamburg",
                             "pudding", "sandwich", "sausage", "wine", "potato"};
    }

    public void make(int i) {
        int    index = ran.nextInt(foods.length);
        String f     = foods[index];
        try {
            Thread.sleep(sleepTime);
            disk.setFood(f + i);
            System.out.println(i + " Cooker " + name + " has maked: " + f);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            make(i+1);
        }
        disk.setFood("exit");
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
