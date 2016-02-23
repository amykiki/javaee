package emp.dao;

import emp.model.EmpException;
import emp.model.User;
import emp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/2/23.
 */
public class UserDaoJDBC implements IUserDao {
    private static String tbName  = "user_tb";
    private static String nameCOL = "USER_NAME";
    private static String pwdCOL  = "PASSWORD";
    private static String nickCOL = "NICKNAME";
    private static String salCOL  = "SALARY";

    @Override
    public boolean addUser(User user) throws EmpException {
        checkUser(user, "add");
        Connection        conn = null;
        PreparedStatement ps   = null;
        ResultSet         rs   = null;
        int               rc   = 0;
        try {
            conn = JDBCUtil.getConnect();
            rs = loadByName(user.getName(), conn, ps);
            if (rs.next()) {
                throw new EmpException(user.getName() + " has alreay be added");
            }
            String sql = "INSERT INTO " + tbName + " VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setDouble(4, user.getSalary());
            rc = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        if (rc == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delUser(String userName) {
        Connection        conn = null;
        PreparedStatement ps   = null;
        try {
            conn = JDBCUtil.getConnect();
            String sql = "delete from " + tbName + " where " + nameCOL + " = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps);
    }

    @Override
    public void updateUser(User user) throws EmpException {
        checkUser(user, "update");
        Connection        conn = null;
        PreparedStatement ps   = null;
        ResultSet         rs   = null;
        try {
            conn = JDBCUtil.getConnect();
            rs = loadByName(user.getName(), conn, ps);
            if (!rs.next()) {
                throw new EmpException("User " + user.getName() + " is not existed");
            }

            String sql = "update " + tbName + " set "
                    + pwdCOL + " = ?, "
                    + nickCOL + " = ?, "
                    + salCOL + " = ? "
                    + "where " + nameCOL + " = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getNickname());
            ps.setDouble(3, user.getSalary());
            ps.setString(4, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
    }

    @Override
    public User load(String userName) throws EmpException{
        Connection        conn = null;
        PreparedStatement ps   = null;
        ResultSet         rs   = null;
        User              u    = null;
        try {
            conn = JDBCUtil.getConnect();
            rs = loadByName(userName, conn, ps);
            if (!rs.next()) {
                throw  new EmpException("User " + userName + " is not existed");
            }
            u = row2User(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return u;
    }

    @Override
    public List<User> loadLists() {
        Connection        conn   = null;
        PreparedStatement ps     = null;
        ResultSet         rs     = null;
        List<User>        uLists = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnect();
            String sql = "select * from " + tbName;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                uLists.add(row2User(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return uLists;
    }

    @Override
    public boolean login(String username, String password) throws EmpException {
        User u = load(username);
        String rightPwd = u.getPassword();
        if (rightPwd.equals(password)) {
            return true;
        } else {
            throw new EmpException("User " + username + " password is not right");
        }
    }

    private User row2User(ResultSet rs) {
        User u = new User();
        try {
            u.setName(rs.getString(nameCOL));
            u.setPassword(rs.getString(pwdCOL));
            u.setNickname(rs.getString(nickCOL));
            u.setSalary(rs.getDouble(salCOL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    private ResultSet loadByName(String name, Connection conn, PreparedStatement ps) {
        ResultSet rs = null;
        try {
            String sql = "select * from " + tbName + " where " + nameCOL + " = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkUser(User user, String action) throws EmpException {
        if (user == null || user.getName() == null || user.getName().equals("")) {
            throw new EmpException("User name is null, can't " + action + " user");
        }
    }
}
