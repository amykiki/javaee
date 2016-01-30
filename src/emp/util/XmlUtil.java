package emp.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * Created by Amysue on 2016/1/30.
 */
public class XmlUtil {
    private static Document userDoc;

    public static Document getUserDoc() {
        if (userDoc == null) {
            SAXReader reader = new SAXReader();
            try {
                System.out.println("test");
                userDoc = reader.read(XmlUtil.class.getClassLoader().getResource("user.xml"));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return userDoc;
    }
}
