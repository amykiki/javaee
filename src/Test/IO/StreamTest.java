package Test.IO;

import java.io.*;

/**
 * Created by Amysue on 2016/1/27.
 */
public class StreamTest {
    public static void main(String[] args) {
        File                 f1  = new File("D:\\Java\\孔浩教程\\java基础\\java专区\\105_Java专题_帮助文档简介.avi");
        File                 f2  = new File("D:\\Test\\test.avi");
        BufferedInputStream  bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(f1));
            bos = new BufferedOutputStream(new FileOutputStream(f2));
            byte[] buff = new byte[1024];
            int    len  = 0;
            while ((len = bis.read(buff)) > 0) {
                bos.write(buff, 0, len);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("not here");
                if (bis != null) {
                    System.out.println("here");
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("not here too");
                if (bos != null) {
                    System.out.println("here too");
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
