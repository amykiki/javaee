package emp.dao;

import emp.model.Dep;
import emp.model.EmpException;
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
public class DepDaoJDBC implements IDepDao {
    private static String tbName  = "dep_tb";
    private static String idCOL   = "ID";
    private static String nameCOL = "DEP_NAME";

    private Connection        conn;
    private PreparedStatement ps;
    private ResultSet         rs;

    public DepDaoJDBC() {
        conn = null;
        ps = null;
        rs = null;
    }

    @Override
    public int addDep(Dep dep) throws EmpException {
        checkDep(dep, "add");
        conn = JDBCUtil.getConnect();
        int rc = 0;
        try {
            rs = loadByName(dep.getName());
            if (rs.next()) {
                throw new EmpException("Department " + dep.getName() + " has alreay be added");
            }
            String sql = "insert into " + tbName + " values(NULL,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dep.getName());
            rc = JDBCUtil.getID(ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return rc;
    }

    @Override
    public boolean delDep(int id) throws EmpException {
        Dep dep = new Dep(id, "");
        checkDep(dep, "delete");
        return JDBCUtil.delRow(tbName, idCOL, id);
    }

    @Override
    public boolean updateDep(Dep dep) throws EmpException {
        checkDep(dep, "update");
        conn = JDBCUtil.getConnect();
        int rc = 0;
        try {
            String name = dep.getName();
            rs = loadById(dep.getId());
            if (!rs.next()) {
                throw new EmpException("Department id = " + dep.getId() + " is not existed");
            }
            rs = loadByName(name);
            if (rs.next()) {
                throw new EmpException("Department " + name + " has alreay existed");
            }
            String sql = "update " + tbName + " set "
                    + nameCOL + " = ? "
                    + "where " + idCOL + " = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dep.getName());
            ps.setInt((2), dep.getId());
            System.out.println(ps);
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
    public Dep load(int id) {
        rs = loadById(id);
        Dep dep = null;
        try {
            if (rs.next()) {
                dep = row2Dep(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return dep;
    }

    @Override
    public Dep load(String name) {
        rs = loadByName(name);
        Dep dep = null;
        try {
            if (rs.next()) {
                dep = row2Dep(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return dep;
    }

    @Override
    public List<Dep> loadLists() {
        List<Dep> depLists = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnect();
            String sql = "select * from " + tbName;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                depLists.add(row2Dep(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return depLists;

    }

    private Dep row2Dep(ResultSet rs) {
        Dep dep = new Dep();
        try {
            dep.setId(rs.getInt(idCOL));
            dep.setName(rs.getString(nameCOL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dep;
    }

    private ResultSet loadById(int id) {
        return JDBCUtil.loadById(tbName, idCOL, id);
    }

    private ResultSet loadByName(String name) {
        String    sql = "select * from " + tbName + " where " + nameCOL + " = ?";
        ResultSet rs  = null;
        try {
            conn = JDBCUtil.getConnect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void checkDep(Dep dep, String action) throws EmpException {
        if (dep == null) {
            throw new EmpException("Null Dep Object, can't " + action + "Dept.");
        } else if (action.equals("add")) {
            if (dep.getName() == null || dep.getName().equals("")) {
                throw new EmpException("NO Dept name, can't add Dept.");
            }
        } else {
            if (dep.getId() <= 0) {
                throw new EmpException("Dept ID is not legal");
            }
        }
    }
}
