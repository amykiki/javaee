package emp.test;

import emp.util.XmlUtil;
import org.dom4j.Document;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Amysue on 2016/1/30.
 */
public class XmlUtilTest {

    @Ignore
    @Test
    public void testFilePath() throws Exception {
        XmlUtil.filePath("user");
    }

    @Test
    public void testGetUserDoc() throws Exception {
        Document doc = XmlUtil.getUserDoc();
        assertEquals("amy", doc.getRootElement().element("user").element("name").getTextTrim());

    }
}