package Test.IO;

import java.io.*;

/**
 * Created by Amysue on 2016/1/14.
 */
public class SystemReaderWriter {
    public static void main(String[] args) {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Java\\ebook\\test.dat")));
            while ((str = br.readLine()) != null) {
                if (str.equalsIgnoreCase("exit")) {
                    System.out.println("input has finished");
                    break;
                }
                pw.println(str);
            }
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("test");
            pw.close();
        }


    }
}
