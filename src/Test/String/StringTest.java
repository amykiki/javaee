package Test.String;

/**
 * Created by Amysue on 2016/1/10.
 */
public class StringTest {
    public static void main(String[] args) {
        String str1 = new String("123");
        String str2 = new String("123");
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        System.out.println("str1.hashCode() = " + str1.hashCode());
        System.out.println("str2.hashCode() = " + str2.hashCode());

        String str3 = "123";
        String str4 = "123";
        System.out.println(str3 == str4);
        System.out.println(str3.equals(str4));
        System.out.println("str3.hashCode() = " + str3.hashCode());
        System.out.println("str4.hashCode() = " + str4.hashCode());
        
        str4 = str1 + str2 + str3;
        System.out.println("str4 = " + str4);

        StringBuilder sb = new StringBuilder();
        str1 = "123";
        str2 = "456";
        str3 = "789";
        sb.append(str1).append(str3);
        StringBuilder sb2 = sb;
        sb2.append(str2);
        str4 = new String(sb2);
        str4 = "   " + str4 + "    ";
        System.out.println("str4.trim() = " + str4.trim());
        str4 = str4.trim();
        System.out.println(str4.replace('1', 'a'));
        str4 = "jkdfka.kdfalfaaaa.kiii.jpgaaa";
        String[] strs = str4.split("a", 4);
        System.out.println(strs.length);
        for (String s: strs) {
            System.out.println(s);
        }
        System.out.println("*" + str4.substring(str4.lastIndexOf("a") + 1));
    }
}
