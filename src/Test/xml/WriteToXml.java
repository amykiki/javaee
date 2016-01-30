package Test.xml;

import Test.util.Person;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amysue on 2016/1/30.
 */
public class WriteToXml {
    public void test() {
        List<Person> ps = new ArrayList<>();
        ps.add(new Person(1, "Amy Zou", "female", 28, 20000));
        ps.add(new Person(2, "Kevin Chou", "male", 30, 25000));
        ps.add(new Person(3, "Eric Chen", "male", 35, 15000));
        ps.add(new Person(4, "Bruce Jiang", "male", 32, 10000));
        ps.add(new Person(5, "Winni Gui", "female", 33, 18000));
        String[]         names = new String[]{"id", "name", "gender", "age", "salary"};
        Document         doc   = DocumentHelper.createDocument();
        Element          root  = doc.addElement("Persons");
        Iterator<Person> iter  = ps.iterator();
        while (iter.hasNext()) {
            Person  p       = iter.next();
            Element newElem = root.addElement("person");
            for (String name : names) {
                String funcName = "get" + captureName(name);
                Object o        = invokeMethod(p, funcName);
                if (name.equals("id")) {
                    newElem.addAttribute(name, o.toString());
                } else {
                    Element textElem = newElem.addElement(name);
                    textElem.addText(o.toString());
                }
            }
        }
        String rootPath = WriteToXml.class.getResource("").getPath();
        rootPath = rootPath.replace("out/production/javaee", "src");
        File      xmlFile = new File(rootPath, "user.xml");
        XMLWriter writer  = null;
        try {
            xmlFile.createNewFile();
            writer = new XMLWriter(new FileWriter(xmlFile), OutputFormat.createPrettyPrint());
            writer.write(doc);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private Object invokeMethod(Object p, String name) {
        Object rc = null;
        try {
            Method method = p.getClass().getMethod(name);
            rc = method.invoke(p);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return rc;
    }

    private String captureName(String name) {
        char[] cs = name.toCharArray();
        if (cs[0] > 96 && cs[0] < 123) {
            cs[0] -= 32;
        }
        return String.valueOf(cs);
    }

}
