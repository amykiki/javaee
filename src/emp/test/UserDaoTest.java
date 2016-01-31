package emp.test;

import emp.dao.UserDao;
import emp.model.User;
import emp.model.empException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/1/30.
 */
public class UserDaoTest {
    private UserDao ud = new UserDao();

    @Ignore
//    @Test (expected = empException.class)
    @Test
    public void testAddUser() throws Exception {
        ud.addUser(new User("Kevin", "8844ki*", "精灵小王子", 55555.5));
        ud.addUser(new User("Amy", "123456", "小公主", 20000));
//        ud.addUser(null);
    }

//    @Ignore
    @Test
    public void testLoad() throws Exception {
        System.out.println(ud.load("Amy").toString());

    }

    @Ignore
    @Test
    public void testDelUser() throws Exception {
//        ud.delUser("Amy");
        ud.delUser("Kevin");
    }

    @Ignore
    @Test
    public void testUpdateUser() throws Exception {
//        ud.updateUser(new User("Amy", "555666", "无敌小公主", 25000));
        ud.updateUser(new User("Kevin", "888**888", "精灵小王子", 66666.6));
    }

    @Ignore
    @Test
    public void testLoadLists() throws Exception {
        List<User> users = ud.loadLists();
        for (User u : users) {
            System.out.println(u);
        }
    }
}