package interview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {
    /**
     * 从字符串中截取第一个英文左括号之前的字符串
     * 正则表达式的写法
     */
    public static void main(String[] args) {
        String str = "北京市(朝阳区)(西城区)(海淀区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(m.group());
        }
    }
}
