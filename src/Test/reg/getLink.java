package Test.reg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/1/28.
 */
public class getLink {
    public List<String> getlinks(String str) {
        List<String> links = new ArrayList<>();
        Pattern p = Pattern.compile("<a.*?\\s+href=\"([^\">]*?)\"\\s+?.*?>(.*?)</a>");
        Matcher m = p.matcher(str);
        while (m.find()) {
            links.add(m.group(1) + " [::::] " + m.group(2));
        }
        return links;
    }
}
