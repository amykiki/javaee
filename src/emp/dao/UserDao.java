package emp.dao;

import emp.model.User;
import emp.model.EmpException;
import emp.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{
    private static final String xmlName = "user";
    private Document userDoc;
    private Element  rootNode;

    public UserDao() {
        this.userDoc = XmlUtil.getUserDoc();
        rootNode = userDoc.getRootElement();
    }

    private void write() {
        XmlUtil.write2Xml(XmlUtil.xmlUsers, userDoc);
    }

    @Override
    public boolean addUser(User user) throws EmpException{
        checkUser(user, "add");
        User u1 = load(user.getName());
        if (u1 != null) {
            System.out.println(user.getName() + " has alreay be added");
            return false;
        }
        user2Xml(user);
        write();
        return true;
    }

    @Override
    public void delUser(String name) {
        Element el = loadElem(name);
        if (el == null) {
            System.out.println("user " + name + " is not exists");
            return;
        }
        rootNode.remove(el);
        write();

    }

    @Override
    public void updateUser(User user) throws EmpException{
        checkUser(user, "update");
        Element el = loadElem(user.getName());
        el.element("name").setText(user.getName());
        el.element("password").setText(user.getPassword());
        el.element("nickname").setText(user.getNickname());
        el.element("salary").setText(String.valueOf(user.getSalary()));
        write();
    }

    @Override
    public User load(String userName) throws EmpException{
        Element el = loadElem(userName);
        if (el != null) {
            User u = xml2User(el);
            return u;
        } else {
            throw new EmpException("user " + userName + " is not exists");
        }
    }

    @Override
    public List<User> loadLists() {
        List<Element> els = rootNode.selectNodes("/users/user");
        List<User> users = new ArrayList<>();
        for (Element e : els) {
            users.add(xml2User(e));
        }
        return users;
    }

    @Override
    public boolean login(String username, String password) throws EmpException{
        User u = load(username);
        if (u == null) {
            throw new EmpException("User " + username + " is not existed");
        } else {
            String rightPwd = u.getPassword();
            if (rightPwd.equals(password)) {
                return true;
            } else {
                throw new EmpException("User " + username + " password is not right");
            }
        }
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

    private void checkUser(User user, String action) throws EmpException{
        if (user == null || user.getName() == null || user.getName().equals("")) {
            throw new EmpException("user name is null, can't " + action + " user");
        }
    }

}
