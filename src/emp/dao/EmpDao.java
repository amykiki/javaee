package emp.dao;

import emp.model.Emp;
import emp.model.EmpException;
import emp.util.Gender;
import emp.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/2/17.
 */
public class EmpDao implements IEmpDao{
    private Document empDoc;
    private Element  rootNode;

    public EmpDao() {
        empDoc = XmlUtil.getEmpDoc();
        rootNode = empDoc.getRootElement();
    }

    private void write() {
        XmlUtil.write2Xml(XmlUtil.xmlEmps, empDoc);
    }

    @Override
    public int addEmp(Emp emp) throws EmpException{
        checkEmp(emp, "add");
        int id = getCount() + 1;
        Element nEmp = rootNode.addElement("emp");
        nEmp.addElement("id").addText(String.valueOf(id));
        nEmp.addElement("name").addText(emp.getName());
        nEmp.addElement("gender").addText(emp.getGender().name());
        nEmp.addElement("salary").addText(String.valueOf(emp.getSalary()));
        nEmp.addElement("depid").addText(String.valueOf(emp.getDepid()));
        setCount(id);
        write();
        return id;
    }

    @Override
    public boolean delEmp(int id) throws EmpException{
        Emp emp = new Emp(id);
        Element e = checkEmp(emp, "del");
        if (e == null) {
            return false;
        }
        rootNode.remove(e);
        write();
        return true;
    }

    @Override
    public boolean updateEmp(Emp emp) throws EmpException{
        Element e = checkEmp(emp, "update");
        if (e == null) {
            return false;
        }
        e.element("name").setText(emp.getName());
        e.element("gender").setText(emp.getGender().name());
        e.element("salary").setText(String.valueOf(emp.getSalary()));
        e.element("depid").setText(String.valueOf(emp.getDepid()));
        write();
        return true;
    }

    @Override
    public Emp loadEmp(int id) throws EmpException{
        Emp emp = new Emp(id);
        Element e = checkEmp(emp, "load");
        if (e == null) {
            return null;
        }
        emp = xml2Emp(e);
        return emp;
    }

    @Override
    public List<Emp> loadList() {
        String xpath = "/emps/emp";
        List<Emp> emps = eles2Emps(xpath);
        return emps;
    }

    @Override
    public int loadByDepId(int depid) {
        String xpath = "/emps/emp[depid=" + depid + "]";
        int num = rootNode.selectNodes(xpath).size();
        return num;
    }

    private Emp xml2Emp(Element e) {
        Emp emp = new Emp();
        emp.setId(Integer.parseInt(e.elementText("id")));
        emp.setName(e.elementText("name"));
        emp.setGender(Gender.valueOf(e.elementText("gender")));
        emp.setSalary(Double.parseDouble(e.elementText("salary")));
        emp.setDepid(Integer.parseInt(e.elementText("depid")));
        return emp;
    }

    private Element checkEmp(Emp emp, String action) throws EmpException{
        if (emp == null) {
            throw new EmpException("Null Emp Object, can't " + action + " Emp.");
        } else if (action.equals("add")) {
            if (emp.getName() == null || emp.getName().equals("")) {
                throw new EmpException("Emp Name is null, can't " + action + " Emp.");
            }
        } else {
            if (emp.getId() <= 0) {
                throw new EmpException("Emp ID is illegal");
            }
            Element e = loadById(emp.getId());
            if (e == null) {
                System.out.println("Can't find " + emp.toString());
            }
            return e;
        }
        return null;
    }

    private Element loadById(int id) {
        String path = "/emps/emp[id=" + id + "]";
        Element e = (Element) rootNode.selectSingleNode(path);
        return e;
    }

    @Override
    public List<Emp> multiLoad(int depid, String name) {
        String xpath = "/emps/emp[";
        String str1 = "";
        String str2 = "";
        if (depid == -1 && name.equals("")) {
            return loadList();
        }
        if (depid > 0) {
            str1 = "depid=" + depid;
        }
        if (!name.equals("")) {
            str2 = "contains(name,'" + name + "')";
        }
        if (!str1.equals("") && !str2.equals("")) {
            xpath += str1 + " and " + str2;
        } else {
            xpath += str1 + str2;
        }
        xpath += "]";
        List<Emp> emps = eles2Emps(xpath);
        return emps;
    }

    private List<Emp> eles2Emps(String xpath) {
        List<Element> eles = rootNode.selectNodes(xpath);
        List<Emp> emps = new ArrayList<>();
        for (Element e : eles) {
            emps.add(xml2Emp(e));
        }
        return emps;
    }
    private void setCount(int count) {
        rootNode.attribute("count").setText(String.valueOf(count));
        return;
    }

    private int getCount() {
        int id = Integer.parseInt(rootNode.attribute("count").getText());
        return id;
    }

}
