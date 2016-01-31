package emp.dao;

import emp.model.User;
import emp.model.empException;
import emp.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Document userDoc;
    private Element  rootNode;
    private static final String xmlName = "user";

    public UserDao() {
        this.userDoc = XmlUtil.getUserDoc();
        rootNode = userDoc.getRootElement();
    }

    private void write() {
        XmlUtil.write2Xml(xmlName, userDoc);
    }

    public void addUser(User user) {
        checkUser(user, "add");
        User u1 = load(user.getName());
        if (u1 != null) {
            System.out.println(user.getName() + " has alreay be added");
            return;
        }
        user2Xml(user);
        write();
    }

    public void delUser(String name) {
        Element el = loadElem(name);
        if (el == null) {
            System.out.println("user " + name + " is not exists");
            return;
        }
        rootNode.remove(el);
        write();

    }

    public void updateUser(User user) {
        checkUser(user, "update");
        Element el = loadElem(user.getName());
        el.element("name").setText(user.getName());
        el.element("password").setText(user.getPassword());
        el.element("nickname").setText(user.getNickname());
        el.element("salary").setText(String.valueOf(user.getSalary()));
        write();
    }

    public User load(String userName) {
        Element el = loadElem(userName);
        if (el != null) {
            User u = xml2User(el);
            return u;
        } else {
            return null;
        }
    }

    public List<User> loadLists() {
        List<Element> els = rootNode.selectNodes("/users/user");
        List<User> users = new ArrayList<>();
        for (Element e : els) {
            users.add(xml2User(e));
        }
        return users;
    }

    private Element loadElem(String userName) {
        String  query = String.format("/users/user[name='%s']", userName);
        Element el    = (Element) rootNode.selectSingleNode(query);
        return el;
    }

    private User xml2User(Element e) {
        User u = new User();
        u.setName(e.element("name").getTextTrim());
        u.setPassword(e.element("password").getTextTrim());
        u.setNickname(e.element("nickname").getTextTrim());
        u.setSalary(Double.parseDouble(e.element("salary").getTextTrim()));
        return u;
    }

    private void user2Xml(User u) {
        Element newElem = rootNode.addElement("user");
        newElem.addElement("name").addText(u.getName());
        newElem.addElement("password").addText(u.getPassword());
        newElem.addElement("nickname").addText(u.getNickname());
        newElem.addElement("salary").addText(String.valueOf(u.getSalary()));
    }

    private void checkUser(User user, String action) {
        if (user == null || user.getName() == null || user.getName().equals("")) {
            throw new empException("user name is null, can't " + action + " user");
        }
    }

}
