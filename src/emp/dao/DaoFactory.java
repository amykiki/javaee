package emp.dao;

/**
 * Created by Amysue on 2016/2/22.
 */
public class DaoFactory {
    public static  IUserDao getUserDao() {
        return new UserDaoJDBC();
    }
}
