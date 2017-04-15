package multithread.ch2;

import java.util.Scanner;

/**
 * Created by zoush on 2017/4/15.
 */

class Processor extends Thread {
    private volatile boolean running = true;
    @Override
    public void run() {
        while (running) {
            System.out.println("Hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
public class App {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press Key to Stop....");
        Scanner scan = new Scanner(System.in);
        System.out.println("=================Pressed " + scan.nextLine());

        proc1.shutdown();

    }

}
