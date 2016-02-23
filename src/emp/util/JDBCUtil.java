package emp.util;

import emp.model.EmpException;

import java.sql.*;

/**
 * Created by Amysue on 2016/2/22.
 */
public class JDBCUtil {
    private static String url    = "jdbc:mysql://localhost:3306/emp_db?useSSL=false";
    private static String user   = "amy";
    private static String passwd = "123";

    public static Connection getConnect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePrestatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static  void close(Connection conn, PreparedStatement ps) {
        closePrestatement(ps);
        closeConnection(conn);
    }

    public static  void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        closeResultSet(rs);
        close(conn, ps);
    }

    public static int getID(PreparedStatement ps, ResultSet rs) {
        String tempSQL = ps.toString();
        int i1 = tempSQL.indexOf(":")+2;
        tempSQL = tempSQL.substring(i1);

        int rc = 0;
        try {
            ps.executeUpdate(tempSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                rc = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rc;
    }

    public static ResultSet loadById(String tbName, String idCOL, int id) {
        Connection conn = getConnect();
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String    sql = "select * from " + tbName + " where " + idCOL + " = ?";
        try {
            conn = JDBCUtil.getConnect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean delRow(String tbName, String idCOL, int id) throws EmpException{
        Connection conn = getConnect();
        PreparedStatement ps = null;
        ResultSet rs  = null;
        int rc = 0;
        try {
            rs = loadById(tbName, idCOL,id);
            if (!rs.next()) {
                throw new EmpException( tbName + " id = " + id + " is not existed");
            }
            String sql = "delete from " + tbName + " where " + idCOL + " = ?";
            conn = getConnect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
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

}
