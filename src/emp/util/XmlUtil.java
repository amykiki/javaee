package emp.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.print.Doc;
import java.io.*;
import java.sql.PseudoColumnUsage;

/**
 * Created by Amysue on 2016/1/30.
 */
public class XmlUtil {
    private static Document userDoc;
    private static Document empsDoc;

    public static Document getUserDoc() {
        if (userDoc == null) {
            SAXReader reader = new SAXReader();
            try {
                userDoc = reader.read(new File(filePath("user")));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return userDoc;
    }

    public static String filePath(String file) {
        String path = XmlUtil.class.getClassLoader().getResource("emp/resources/" + file + ".xml").getPath();
        path = path.replace("out/production/javaee", "src");
        return path;
    }

    public static void write2Xml(String file, Document doc) {
        String path = filePath(file);
        XMLWriter out = null;
        try {
            out = new XMLWriter(new FileOutputStream(path), OutputFormat.createPrettyPrint());
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
