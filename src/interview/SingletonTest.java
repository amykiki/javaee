package interview;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 查看通过反射或者序列化反序列化怎么破坏单例的
 */
public class SingletonTest {
    public static void main(String[] args) throws Exception {
        //直接调用getInstance得到的对象是单例的
        /**
         * 输出
         * interview.Singleton2is Initialing..., CurrentDate 1513861240425
         * sobj1 == sobj2:true
         */
        Singleton2 sobj1 = Singleton2.getInstance();
        Singleton2 sobj2 = Singleton2.getInstance();
        System.out.println("sobj1 == sobj2:" + (sobj1 == sobj2));



        //通过反射破坏单例模式,此时构造函数没有对初始化函数多次调用报异常
        /**
         * 输出
         * interview.Singleton2is Initialing..., CurrentDate 1513861240426
         * 通过反射获取了Singleton2新实例
         * sobj1 == sobj3:false
         */
        //构造函数对多次调用报异常
        /**
         * 输出
         * Exception in thread "main" java.lang.reflect.InvocationTargetException
         * at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
         * at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
         * at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
         * at java.lang.reflect.Constructor.newInstance(Constructor.java:526)
         * at interview.SingletonTest.main(SingletonTest.java:32)
         * Caused by: java.lang.RuntimeException: 多次调用初始化函数
         * at interview.Singleton2.<init>(Singleton2.java:10)
         * ... 5 more
         */
        Class<Singleton2> clazz = (Class<Singleton2>) Class.forName("interview.Singleton2");
        Constructor<Singleton2> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Singleton2 sobj3 = constructor.newInstance();
        System.out.println("通过反射获取了Singleton2新实例");
        System.out.println("sobj1 == sobj3:" + (sobj1 == sobj3));

        String dir = "D:\\codeproject\\githubProject\\javaee\\out\\";
        String objFile = dir + "object.out";
        FileOutputStream fileOutputStream = new FileOutputStream(objFile);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
    }
}
