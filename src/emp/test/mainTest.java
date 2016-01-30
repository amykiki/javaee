package emp.test;

import emp.util.XmlUtil;
import org.dom4j.Document;

/**
 * Created by Amysue on 2016/1/30.
 */
public class mainTest {
    public static void main(String[] args) {
        XmlUtil xmlU = new XmlUtil();
        Document uDoc = XmlUtil.getUserDoc();
    }
}
