package Test.MultilThread.ProduceQueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Amysue on 2016/1/21.
 */
public class Disk {
    private Queue<String> food;
    private int           capacity;
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public Disk(int aCapacity) {
        capacity = aCapacity;
        food = new ArrayDeque<>(capacity);
    }

    public String getFood() {
        String str = null;
        synchronized (food) {
            if (food.size() <= 0) {
                try {
                    System.out.println("Customer is waiting...");
                    food.notify();
                    food.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                str = food.poll();
                food.notify();
//                System.out.println("customer has something to eat");
            }
        }
        return str;
    }

    public void setFood(String aFood) {
        synchronized (food) {
            System.out.println();
            System.out.println();
            if (food.size() >= capacity) {
                try {
                    System.out.println("Cooker is waiting");
                    food.notify();
                    food.wait();
                    food.offer(aFood);
                    System.out.println(food);

//                    System.out.println("lalala, customer eat");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                food.offer(aFood);
                System.out.println(food);
                food.notify();
            }

        }
    }

    public Queue<String> foodList() {
        return food;
    }

}
