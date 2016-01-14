package Test.IO;

import java.io.*;
import java.util.Date;

/**
 * Created by Amysue on 2016/1/12.
 */
public class FileInputTest {
    public static void main(String[] args) {
        File f1 = new File("E:\\Music\\周杰伦-叶惠美.flac");
        File f11 = new File("E:\\Music\\周杰伦-叶惠美.flac");
        File f2 = new File("D:\\Java\\ebook\\jay1.flac");
        File f21 = new File("D:\\Java\\ebook\\jay2.flac");
        FileInputStream fis = null;
        FileInputStream fis2 = null;
        FileOutputStream fos = null;
        FileOutputStream fos2 = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(f1);
            fos = new FileOutputStream(f2);
            fis2 = new FileInputStream(f11);
            fos2 = new FileOutputStream(f21);
            bis = new BufferedInputStream(fis2);
            bos = new BufferedOutputStream(fos2);
            byte[] buf = new byte[1024];
            int len = 0;
            long time1 = new Date().getTime();
            while ((len = fis.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            long time2 = new Date().getTime();
            while ((len = bis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
            bos.flush();
            long time3 = new Date().getTime();
            long gap1 = time2 - time1;
            long gap2 = time3 - time2;
            System.out.println("time1 = " + time1);
            System.out.println("time2 = " + time2);
            System.out.println("time3 = " + time3);
            System.out.println("gap1 = " + gap1);
            System.out.println("gap2 = " + gap2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally is running...");
            try {
                if (fis != null) {
                    System.out.println("input file stream has not been closed");
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this is last line");
    }
}
