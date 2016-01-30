package Test.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amysue on 2016/1/29.
 */
public class MainTest {
    public static void main(String[] args) {
        WriteToXml wtx = new WriteToXml();
        wtx.test();


    }

    public void test1() {
        MyXpath myPath = new MyXpath();
        Document doc = myPath.getDoc("Test/xml/bookstore.xml");
        System.out.println(doc.getRootElement().getName());
        Element root = doc.getRootElement();
        System.out.println(root.selectNodes("book"));
        System.out.println(root.selectNodes("//@lang='eng'"));

//        myPath.printList(root.selectNodes("//@lang"));
        myPath.printElemList(root.selectNodes("//book[title[@lang='eng']]"));
        myPath.printElemList(root.selectNodes("//book[contains(title, 'java')]"));
        myPath.printElemText(root.selectNodes("/bookstore/book[2]/title"));
    }

    public void testMain() {
        SAXReader saxr = new SAXReader();
        Document  doc  = null;
       /* for load resource file test
        MainTest m = new MainTest();
        System.out.println(MainTest.class.getResource("test.xml").getPath());  // resource under current package
        System.out.println(m.getClass().getResource("test.xml").getPath());  // resource under current package
        System.out.println(m.getClass().getClassLoader().getResource("books.xml").getPath()); //resource under src file
        System.out.println(MainTest.class.getClassLoader().getResource("books.xml").getPath());
        */

        try {
//            File f = new File(MainTest.class.getResource("test.xml").getPath());
//            doc = saxr.read(f);
            doc = saxr.read(MainTest.class.getResourceAsStream("test.xml"));
            Element re = doc.getRootElement();
            System.out.println(re.getName());
            List<Element> eles = re.elements();
            for (Element e : eles) {
                System.out.print(e.getName() + ":" + "id = " + e.attributeValue("id"));
                Iterator<Element> iter = e.elementIterator();
                while (iter.hasNext()) {
                    Element el = iter.next();
                    System.out.print(" " + el.getName() + " = " + e.elementText(el.getName()));
                }
                System.out.println();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
