package Test.IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Amysue on 2016/1/14.
 */
public class SystemInputTest {
    public static void main(String[] args) {
        InputStream is = System.in;
        byte[] buf = new byte[1024];
        try {
            while (true) {
                int len;
                while ((len = is.read(buf)) > 0) {
                    String str = new String(buf, 0, len);
                    str = str.trim();
                    if (str.equalsIgnoreCase("exit")) {
                        System.exit(0);
                    }
                    System.out.println("*"+ str + "*");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
