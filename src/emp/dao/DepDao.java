package emp.dao;

import com.sun.msv.datatype.xsd.regex.REUtil;
import emp.model.Dep;
import emp.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * Created by Amysue on 2016/2/14.
 */
public class DepDao {
    private Document depDoc;
    private Element rootNode;

    public DepDao() {
        depDoc = XmlUtil.getDepDoc();
        rootNode = depDoc.getRootElement();
    }

    private void write() {
        XmlUtil.write2Xml(XmlUtil.xmlDeps, depDoc);
    }

    public boolean addDep(Dep dep) {
        String name = dep.getName();
        Element e = loadByName(name);
        if (e != null) {
            System.out.println("Departmen " + name + " has alreay be added");
            return false;
        }

    }

    private Element loadById(int id) {
        String xmlPath = String .format("/deps/dep[id='%d']", id);
        Element e = (Element) rootNode.selectSingleNode(xmlPath);
        if (e == null) {
            return null;
        }
        return e;

    }

    private Element loadByName(String name) {
        String xmlPath = String .format("/deps/dep[name='%s']", name);
        Element e = (Element) rootNode.selectSingleNode(xmlPath);
        if (e == null) {
            return null;
        }
        return e;

    }

    private Dep xml2Dep(Element element) {
        Dep dep = new Dep();
        dep.setName(element.elementTextTrim("name"));
        dep.setId(Integer.parseInt(element.elementText("id")));
        return dep;
    }

    private int getId() {
        
    }
}
