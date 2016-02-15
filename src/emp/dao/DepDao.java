package emp.dao;

import emp.model.Dep;
import emp.model.empException;
import emp.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/2/14.
 */
public class DepDao {
    private Document depDoc;
    private Element  rootNode;

    public DepDao() {
        depDoc = XmlUtil.getDepDoc();
        rootNode = depDoc.getRootElement();
    }

    private void write() {
        XmlUtil.write2Xml(XmlUtil.xmlDeps, depDoc);
    }

    public int addDep(Dep dep) {
        checkDep(dep, "add");
        String  name = dep.getName();
        Element e    = loadByName(name);
        if (e != null) {
            System.out.println("Department " + name + " has alreay be added");
            return -1;
        }
        int id = getCount() + 1;
        dep.setId(id);
        dep2Xml(dep);
        setCount(id);
        write();
        return id;
    }

    public boolean updateDep(Dep dep) {
        Element e = checkDep(dep, "update");
        if (e == null) {
            return false;
        }
        e.element("name").setText(dep.getName());
        write();
        return true;
    }

    public boolean delDep(int id) {
        Dep     dep = new Dep(id, "");
        Element e   = checkDep(dep, "delete");
        if (e == null) {
            return false;
        }
        rootNode.remove(e);
        write();
        return true;
    }

    public Dep load(int id) {
        Element e = loadById(id);
        if (e != null) {
            Dep dep = xml2Dep(e);
            return dep;
        } else {
            return null;
        }
    }

    public List<Dep> loadLists() {
        List<Element> eles = rootNode.selectNodes("/deps/dep");
        List<Dep> deps = new ArrayList<>();
        for (Element e : eles) {
            deps.add(xml2Dep(e));
        }
        return deps;
    }

    private Element checkDep(Dep dep, String action) {
        if (dep == null) {
            throw new empException("null Dep object, can't " + action + " Dept.");
        } else if (action.equals("add")) {
            if (dep.getName().equals("") || dep.getName() == null) {
                throw new empException("NO Dept name, can't add Dept.");
            }
        } else {
            if (dep.getId() <= 0) {
                throw new empException("Dept ID is not legal");
            }
            Element e = loadById(dep.getId());
            if (e == null) {
                System.out.println("Can't find Dept: id=" + dep.getId() + ", name= " + dep.getName());
            }
            return e;
        }
        return null;
    }

    private Element loadById(int id) {
        String  xmlPath = String.format("/deps/dep[id='%d']", id);
        Element e       = (Element) rootNode.selectSingleNode(xmlPath);
        if (e == null) {
            return null;
        }
        return e;

    }

    private Element loadByName(String name) {
        String  xmlPath = String.format("/deps/dep[name='%s']", name);
        Element e       = (Element) rootNode.selectSingleNode(xmlPath);
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

    private void dep2Xml(Dep dep) {
        Element e = rootNode.addElement("dep");
        e.addElement("id").setText(String.valueOf(dep.getId()));
        e.addElement("name").setText(dep.getName());
    }

    private int getCount() {
        int id = Integer.parseInt(rootNode.attribute("count").getText());
        return id;
    }

    private void setCount(int count) {
        rootNode.attribute("count").setText(String.valueOf(count));
        return;
    }
}
