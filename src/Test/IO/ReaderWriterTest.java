package Test.IO;

import java.io.*;

/**
 * Created by Amysue on 2016/1/14.
 */
public class ReaderWriterTest {
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new FileReader("D:\\Java\\ebook\\test.dat"));
            bw = new BufferedWriter(new FileWriter("D:\\Java\\ebook\\test2.dat"));
            pw = new PrintWriter(bw);
            String str;
            while ((str = br.readLine()) != null) {
                pw.println(str);
            }
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }

    }
}
