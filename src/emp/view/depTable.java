package emp.view;

import emp.dao.DepDao;
import emp.dao.EmpDao;
import emp.model.Dep;
import emp.model.Emp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created by Amysue on 2016/2/17.
 */
public class DepTable extends DefaultTableModel {
    private DepDao dd;
    private EmpDao ed;

    public DepTable() {
        dd = new DepDao();
        ed = new EmpDao();
        this.addColumn(DEPCOL.ID.name());
        this.addColumn(DEPCOL.NAME.name());
        this.addColumn(DEPCOL.PEPCOUNT.name());

        List<Dep> deps = dd.loadLists();
        for (Dep dep : deps) {
            addData2Row(dep, ed.loadByDepId(dep.getId()));
        }

    }

    public Dep removeDep(int row) {
        int id    = (int) getValueAt(row, DEPCOL.ID.getCode());
        int count = (int) getValueAt(row, DEPCOL.PEPCOUNT.getCode());
        Dep dep   = dd.load(id);
        if (count > 0) {
            ManagerFrame.showMsg(null, dep.getName() + " has employee in it, can't be deleted");
            return null;
        }
        int result = ManagerFrame.showConfim(null, "Are you sure to Delete " + dep.getName());
        if (result == JOptionPane.NO_OPTION) {
            return null;
        }
        dd.delDep(id);
        this.removeRow(row);
        return dep;
    }

    private void addData2Row(Dep dep, int count) {
        Vector data = new Vector();
        data.add(dep.getId());
        data.add(dep.getName());
        data.add(count);
        this.addRow(data);
    }

    public Dep addDep(String name) {
        Dep dep = new Dep(name);
        int id  = dd.addDep(dep);
        dep.setId(id);
        addData2Row(dep, 0);
        return dep;
    }

    public Dep updateDep(int row, String name) {
        int id  = (int) getValueAt(row, DEPCOL.ID.getCode());
        Dep dep = new Dep(id, name);
        dd.updateDep(dep);
        this.setValueAt(name, row, DEPCOL.NAME.getCode());
        return dep;
    }

    public Dep data2Dep(int row) {
        int id  = (int) getValueAt(row, DEPCOL.ID.getCode());
        Dep dep = dd.load(id);
        return dep;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void updatePepCount(int depid, String action) {
        int row = -1;
        for (int i = 0; i < getRowCount(); i++) {
            if ((int) getValueAt(i, DEPCOL.ID.getCode()) == depid) {
                row = i;
                break;
            }
        }
        if (row >= 0) {
            int count = (int) getValueAt(row, DEPCOL.PEPCOUNT.getCode());
            if (action.equals("del")) {
                count--;
            } else if (action.equals("add")) {
                count++;
            }
            setValueAt(count, row, DEPCOL.PEPCOUNT.getCode());
        }
    }

    public enum DEPCOL {
        ID(0), NAME(1), PEPCOUNT(2);

        private int code;

        DEPCOL(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

}
