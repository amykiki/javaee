package emp.dao;

import emp.model.EmpException;
import emp.model.User;

import java.util.List;

/**
 * Created by Amysue on 2016/2/22.
 */
public interface IUserDao {

    public boolean addUser(User user) throws EmpException;
    public void delUser(String userName);
    public void updateUser(User user) throws EmpException;
    public User load(String userName) throws EmpException;
    public List<User> loadLists();
    public boolean login(String username, String password) throws EmpException;

}
