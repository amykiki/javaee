package multithread.ch04_multiplelocks;

/**
 * Created by zoush on 2017/4/16.
 */
public class App {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.main();
    }
}
