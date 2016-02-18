package Test.reg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/1/27.
 */
public class MainTest {
    public static void main(String[] args) {
//        System.out.println("15663.55".matches("\\d+(\\.\\d+)?"));
//        System.out.println("0".matches("\\d||[1-2]\\d||3[0-5]"));
//        System.out.println("192.168.22.123".matches("1?\\d{0,2}"));

        String str33 = "Diagon Alley";
        if (str33.matches("^[a-zA-Z]\\w*(?:\\s\\w+)*")) {
            System.out.println("true");
        } else {
            System.out.println("failed");
        }
        Pattern p = Pattern.compile("(\\d{2})(\\d{1})");
        Matcher m = p.matcher("22228858-3333-4444");
        System.out.println(m.matches());
        m.reset();
//        System.out.println(m.find());
//        System.out.println(m.group());
//        System.out.println(m.find());
//        System.out.println(m.group());
//        System.out.println(m.find());
//        System.out.println(m.group());

        while (m.find()) {
            System.out.println(m.group(0) + "[" + m.start() + "," + m.end() + "]");
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }

        String str = "18930161861";
        System.out.println(str.replaceAll("\\d{4}$", "*"));
        String ids = "511525198704180348, 520145198605020028, 523014960205111";
        p = Pattern.compile("((\\d{6})(\\d{8})\\d{4}|(\\d{6})(\\d{6})\\d3)");
        m = p.matcher(ids);
        while (m.find()) {
            System.out.println(m.group());
            System.out.println("From:" + m.group(2) + "," + "Birth:" + m.group(3));
        }

        String h = "<table><td>hello</td><td>every one</td><td>world lala</td></table";
        p = Pattern.compile("<td>(.*?)</td>");
        m = p.matcher(h);
        while (m.find()) {
            System.out.println(m.group(1));
            System.out.println(m.start() + "," + m.end());
        }
        System.out.println("y".matches("x.|y"));
        System.out.println("quit".matches("q(?=u)uit"));
        System.out.println("John".matches("\\b\\w+(?<!s)\\b"));
//        System.out.println("a".matches("\\b\\w*[^s\\W]\\b"));
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader("D:\\Java\\www.sina.com.cn.html"));
//            String        line = null;
//            StringBuilder sb   = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            getLink gl = new getLink();
//            List<String> links = gl.getlinks(sb.toString());
//            for (String link : links) {
//                System.out.println(link);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
    }
}
