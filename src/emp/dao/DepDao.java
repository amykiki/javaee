package emp.dao;

import emp.model.Dep;
import emp.model.EmpException;
import emp.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/2/14.
 */
public class DepDao implements IDepDao{
    private Document depDoc;
    private Element  rootNode;

    public DepDao() {
        depDoc = XmlUtil.getDepDoc();
        rootNode = depDoc.getRootElement();
    }

    private void write() {
        XmlUtil.write2Xml(XmlUtil.xmlDeps, depDoc);
    }

    @Override
    public int addDep(Dep dep) throws EmpException{
        checkDep(dep, "add");
        String  name = dep.getName();
        Element e    = loadByName(name);
        if (e != null) {
            throw  new EmpException("Department " + name + " has alreay be added");
        }
        int id = getCount() + 1;
        dep.setId(id);
        dep2Xml(dep);
        setCount(id);
        write();
        return id;
    }

    @Override
    public boolean updateDep(Dep dep) throws EmpException{
        Element e = checkDep(dep, "update");
        if (e == null) {
            return false;
        }
        String name = dep.getName();
        Element e2    = loadByName(name);
        if (e2 != null) {
            throw  new EmpException("Department " + name + " has alreay existed");
        }
        e.element("name").setText(name);
        write();
        return true;
    }

    @Override
    public boolean delDep(int id) throws EmpException{
        Dep     dep = new Dep(id, "");
        Element e   = checkDep(dep, "delete");
        if (e == null) {
            return false;
        }
        rootNode.remove(e);
        write();
        return true;
    }

    @Override
    public Dep load(int id) {
        Element e = loadById(id);
        if (e != null) {
            Dep dep = xml2Dep(e);
            return dep;
        } else {
            return null;
        }
    }

    @Override
    public Dep load(String name) {
        Element e = loadByName(name);
        if (e != null) {
            Dep dep = xml2Dep(e);
            return dep;
        } else {
            return null;
        }
    }

    @Override
    public List<Dep> loadLists() {
        List<Element> eles = rootNode.selectNodes("/deps/dep");
        List<Dep> deps = new ArrayList<>();
        for (Element e : eles) {
            deps.add(xml2Dep(e));
        }
        return deps;
    }

    private Element checkDep(Dep dep, String action) throws EmpException{
        if (dep == null) {
            throw new EmpException("null Dep object, can't " + action + " Dept.");
        } else if (action.equals("add")) {
            if (dep.getName().equals("") || dep.getName() == null) {
                throw new EmpException("NO Dept name, can't add Dept.");
            }
        } else {
            if (dep.getId() <= 0) {
                throw new EmpException("Dept ID is not legal");
            }
            Element e = loadById(dep.getId());
            if (e == null) {
                throw new EmpException("Can't find Dept: id=" + dep.getId() + ", name= " + dep.getName());
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
