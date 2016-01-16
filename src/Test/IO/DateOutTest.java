package Test.IO;

import java.io.*;

/**
 * Created by Amysue on 2016/1/14.
 */
public class DateOutTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        DataOutputStream dos = null;
        DataInputStream  dis = null;
        try {
            fos = new FileOutputStream("D:\\Java\\ebook\\test.dat", true);
            String str1 = String.valueOf(123456);
            str1 = "this is end";
            byte[] buff = str1.getBytes();
//            fos.write(buff, 0, buff.length);
            int len;
//            str1 = String.valueOf(8899);
//            buff = str1.getBytes();
//            fos.write(buff, 0, buff.length);
            fis = new FileInputStream("D:\\Java\\ebook\\test.dat");
            while ((len = fis.read(buff)) > 0) {
//                System.out.write(buff);
                System.out.write(buff, 0, len);
            }
            System.out.println();
            dos = new DataOutputStream(new FileOutputStream("D:\\Java\\ebook\\test2.dat"));
            dos.writeInt(1);
//            dos.writeInt(88);
//            dos.writeInt(777);
//            dos.writeInt(1);
//            dos.writeInt(222);
            dos.flush();

            dis = new DataInputStream(new FileInputStream("D:\\Java\\ebook\\test2.dat"));
            System.out.println(dis.readInt());
//            System.out.println(dis.readInt());
//            System.out.println(dis.readInt());
//            System.out.println(dis.readInt());
//            System.out.println(dis.readInt());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
