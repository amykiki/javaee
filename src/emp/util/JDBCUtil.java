package emp.util;

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


}
