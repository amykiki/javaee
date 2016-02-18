package emp.view;

import emp.dao.DepDao;
import emp.dao.EmpDao;
import emp.model.Dep;
import emp.model.Emp;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created by Amysue on 2016/2/17.
 */
public class empTable extends DefaultTableModel{
    private EmpDao ed;
    private DepDao dd;

    public empTable() {
        ed = new EmpDao();
        dd = new DepDao();

        this.addColumn(EMPCOL.ID.name());
        this.addColumn(EMPCOL.NAME.name());
        this.addColumn(EMPCOL.GENDER.name());
        this.addColumn(EMPCOL.SALARY.name());
        this.addColumn(EMPCOL.DEPARTMENT.name());

        List<Emp> emps = ed.loadList();
        for (Emp emp : emps) {
            addEmpRow(emp);
        }
    }

    private void addEmpRow(Emp emp) {
        Vector data = new Vector();
        data.add(emp.getId());
        data.add(emp.getName());
        data.add(emp.getGender());
        data.add(emp.getSalary());
        data.add(dd.load(emp.getDepid()).getName());
        this.addRow(data);
    }

    public void updateDep(int depid, String depname) {
        List<Emp> emps = ed.multiLoad(depid, null);
        if (emps.size() > 0) {
            String newName = dd.load(emps.get(0).getDepid()).getName();
            for (int i = 0; i < getRowCount(); i++) {
                String oldName = getDepName(i);
                if (oldName.equals(depname)) {
                    setValueAt(newName, i, EMPCOL.DEPARTMENT.getCode());
                }

            }
        }
    }

    public void search(int depid, String name) {
        List<Emp> emps = ed.multiLoad(depid, name);
        removeAllRow();
        for (Emp emp : emps) {
            addEmpRow(emp);
        }
    }

    private void removeAllRow() {
        for (int i = getRowCount() - 1; i > -1; i--) {
            removeRow(i);
        }
    }

    public int removeEmp(int row) {
        String depName = getDepName(row);
        int depid = getDepId(depName);
        ed.delEmp(getId(row));
        removeRow(row);
        return depid;
    }

    private int getId(int row) {
        int id = (int)getValueAt(row, EMPCOL.ID.getCode());
        return id;
    }
    private String getDepName(int row) {
        String name = (String)getValueAt(row, EMPCOL.DEPARTMENT.getCode());
        return name;
    }

    private Dep getDep(String name) {
        Dep dep = dd.load(name);
        return dep;
    }

    private int getDepId(String name) {
        Dep dep = getDep(name);
        if (dep != null) {
            return dep.getId();
        }
        return 0;
    }

    public enum EMPCOL {
        ID(0), NAME(1), GENDER(2), SALARY(3), DEPARTMENT(4);


        private int code;
        EMPCOL(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
