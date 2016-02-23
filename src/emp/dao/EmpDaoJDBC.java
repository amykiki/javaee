package emp.dao;

import emp.model.Emp;
import emp.model.EmpException;
import emp.util.Gender;
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
public class EmpDaoJDBC implements IEmpDao {
    private static String tbName    = "emp_tb";
    private static String idCOL     = "ID";
    private static String nameCOL   = "EMP_NAME";
    private static String genderCOL = "GENDER";
    private static String salaryCOL = "SALARY";
    private static String depidCOL  = "DEP_ID";

    private Connection        conn;
    private PreparedStatement ps;
    private ResultSet         rs;

    public EmpDaoJDBC() {
        conn = null;
        ps = null;
        rs = null;
    }

    @Override
    public int addEmp(Emp emp) throws EmpException {
        checkEmp(emp, "add");
        int id = 0;
        try {
            conn = JDBCUtil.getConnect();
            String sql = "insert into " + tbName + " values(NULL,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getGender().toString());
            ps.setDouble(3, emp.getSalary());
            ps.setInt(4, emp.getDepid());
            id = JDBCUtil.getID(ps, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(conn, ps, rs);
        return id;
    }

    @Override
    public boolean delEmp(int id) throws EmpException {
        Emp emp = new Emp(id);
        checkEmp(emp, "delete");
        return JDBCUtil.delRow(tbName, idCOL, id);
    }

    @Override
    public boolean updateEmp(Emp emp) throws EmpException {
        checkEmp(emp, "update");
        conn = JDBCUtil.getConnect();
        int rc = 0;
        try {
            String name = emp.getName();
            rs = loadById(emp.getId());
            if (!rs.next()) {
                throw new EmpException("EMp id = " + emp.getId() + " is not existed");
            }
            String sql = "update " + tbName + " set "
                    + nameCOL + " = ? "
                    + genderCOL + " = ?"
                    + salaryCOL + " = ?"
                    + depidCOL + " = ?"
                    + "where " + idCOL + " = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getGender().toString());
            ps.setDouble(3, emp.getSalary());
            ps.setInt(4, emp.getDepid());
            ps.setInt((5), emp.getId());
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
    public Emp loadEmp(int id) throws EmpException {
        rs = loadById(id);
        Emp emp = null;
        try {
            if (rs.next()) {
                emp = row2Emp(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return emp;
    }

    @Override
    public List<Emp> loadList() {
        List<Emp> empLists = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnect();
            String sql = "select * from " + tbName;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                empLists.add(row2Emp(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return empLists;
    }

    @Override
    public List<Emp> multiLoad(int depid, String name) {
        if (depid < 1 && (name == null || name.equals(""))) {
            return loadList();
        }
        List<Emp> empLists = new ArrayList<>();
        conn = JDBCUtil.getConnect();
        String sql = "select * from " + tbName + " where 1=1";
        if (depid > 0) {
            sql += " and " + depidCOL + " = " + depid;
        }
        if (name != null && !name.equals("")) {
            sql += " and " + nameCOL + " like '%" + name + "%'";
        }
        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                empLists.add(row2Emp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return empLists;
    }

    @Override
    public int loadByDepId(int depid) {
        conn = JDBCUtil.getConnect();
        String sql = "select count(*) from " + tbName + " where " + depidCOL + " = ?";
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, depid);
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(conn, ps, rs);
        return count;
    }

    private Emp row2Emp(ResultSet rs) {
        Emp emp = new Emp();
        try {
            emp.setId(rs.getInt(idCOL));
            emp.setName(rs.getString(nameCOL));
            if (rs.getString(genderCOL).equals("MALE")) {
                emp.setGender(Gender.MALE);
            } else {
                emp.setGender(Gender.FEMALE);
            }
            emp.setSalary(rs.getDouble(salaryCOL));
            emp.setDepid(rs.getInt(depidCOL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    private ResultSet loadById(int id) {
        return JDBCUtil.loadById(tbName, idCOL, id);
    }

    private void checkEmp(Emp emp, String action) throws EmpException {
        if (emp == null) {
            throw new EmpException("Null Emp Object, can't " + action + "Emp.");
        } else if (action.equals("add")) {
            if (emp.getName() == null || emp.getName().equals("")) {
                throw new EmpException("NO Emp name, can't add Emp.");
            }
        } else {
            if (emp.getId() <= 0) {
                throw new EmpException("Emp ID is not legal");
            }
        }
    }
}
