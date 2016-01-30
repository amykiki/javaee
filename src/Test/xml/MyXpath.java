package Test.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Amysue on 2016/1/29.
 */
public class MyXpath {
    public Document getDoc(String pathToXml) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(this.getClass().getClassLoader().getResourceAsStream(pathToXml));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public <T> void printList(List<T> list) {
        for (T e : list) {
            System.out.println(e.toString());
        }
    }

    public void printElemList(List<Element> list) {
        for (Element e : list) {
            System.out.print(e.getName() + ":");
            Iterator<Element> iter = e.elementIterator();
            while (iter.hasNext()) {
                Element el = iter.next();
                System.out.print(" " + el.getName() + " = " + e.elementText(el.getName()));
            }
            System.out.println();
        }
        System.out.println();
    }


    public void printElemText(List<Element> list) {
        for (Element e : list) {
            System.out.println(e.getTextTrim());

        }
    }

}
