package Test.IO;

import java.io.*;

/**
 * Created by Amysue on 2016/1/14.
 */
public class DateOutTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        FileOutputStream fos2 = null;
        DataOutputStream dos = null;
        DataInputStream  dis = null;
        try {
            fos = new FileOutputStream("D:\\Java\\ebook\\test.dat");
            String str1 = String.valueOf(1);
            byte[] buff = str1.getBytes();
            fos.write(buff, 0, buff.length);
//            str1 = String.valueOf(8899);
//            buff = str1.getBytes();
//            fos.write(buff, 0, buff.length);

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
