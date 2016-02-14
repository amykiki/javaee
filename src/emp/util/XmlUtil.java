package emp.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.TreeMap;


/**
 * Created by Amysue on 2016/1/30.
 */
public class XmlUtil {
    public static final String xmlUsers = "users";
    public static final String xmlDeps  = "deps";
    private static Document userDoc;
    private static Document depDoc;

    public static Document getUserDoc() {
        userDoc = getDoc(xmlUsers, userDoc);
        return userDoc;
    }

    public static Document getDepDoc() {
        depDoc = getDoc(xmlDeps, depDoc);
        return depDoc;
    }

    private static Document getDoc(String fileName, Document doc) {
        if (doc == null) {
            SAXReader reader = new SAXReader();
            try {
                doc = reader.read(new File(filePath(fileName)));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return doc;
    }

    public static String filePath(String file) {
        String path = XmlUtil.class.getClassLoader().getResource(
                "emp/resources/" + file + ".xml").getPath();
        path = path.replace("out/production/javaee", "src");
        return path;
    }

    public static void write2Xml(String file, Document doc) {
        String    path = filePath(file);
        XMLWriter out  = null;
        try {
            out = new XMLWriter(
                    new FileOutputStream(path), OutputFormat.createPrettyPrint());
            out.write(doc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
