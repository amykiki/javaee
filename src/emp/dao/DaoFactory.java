package emp.dao;

/**
 * Created by Amysue on 2016/2/22.
 */
public class DaoFactory {
    public static  IUserDao getUserDao() {
        return new UserDaoJDBC();
//        return new UserDao();
    }

    public static IDepDao getDepDao() {
        return new DepDaoJDBC();
//        return new DepDao();
    }

    public static IEmpDao getEmpDao() {
        return new EmpDaoJDBC();
//        return new EmpDao();
    }
}
