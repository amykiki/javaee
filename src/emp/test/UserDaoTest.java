package emp.test;

import emp.dao.DaoFactory;
import emp.dao.IDepDao;
import emp.dao.IUserDao;
import emp.dao.UserDao;
import emp.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Amysue on 2016/1/30.
 */
public class UserDaoTest {
//    private UserDao ud = new UserDao();
private IUserDao ud = DaoFactory.getUserDao();

    @Test
    public void testAddUser() throws Exception {
//        ud.addUser(new User("Kevin", "8844ki*", "精灵小王子", 55555.5));
//        ud.addUser(new User("Kevin2", "8844ki*///", "精灵小王子", 55555.5));
//        ud.addUser(new User("Amy", "123456", "小公主", 20000));
//        ud.addUser(new User());
//        Assert.assertTrue(ud.addUser(new User("harry", "*&^%$>>>", "哈利波特", 20000)));
        Assert.assertTrue(ud.addUser(new User("赫敏", "5858", "herm", 99999.987)));
    }

    @Test
    public void testLoad() throws Exception {
        System.out.println(ud.load("Amy").toString());

    }

    @Test
    public void testDelUser() throws Exception {
//        ud.delUser("Amy");
        ud.delUser("赫敏");
    }

    @Test
    public void testUpdateUser() throws Exception {
//        ud.updateUser(new User("Amy", "555666", "无敌小公主", 25000));
        ud.updateUser(new User("Kevin", "888**888", "精灵小王子", 66666.6));
    }

    @Test
    public void testLoadLists() throws Exception {
        List<User> users = ud.loadLists();
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testLogin() throws Exception {
        ud.login("Amy", "555666");
    }
}