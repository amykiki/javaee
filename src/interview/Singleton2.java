package interview;

import java.util.Date;

public class Singleton2 {
    private volatile static Singleton2 instance;
    private Date date;
    private Singleton2() {
        /*if (instance != null) {
            throw new RuntimeException("多次调用初始化函数");
        }*/
        date = new Date();
        System.out.println(this.getClass().getName()  + "is Initialing..., CurrentDate " + date.getTime());
    }
    public static Singleton2 getInstance() {
        if(instance == null) {
            synchronized(Singleton.class) {
                if(instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

}
