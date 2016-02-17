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
public class depTable extends DefaultTableModel {
    private DepDao dd;
    private EmpDao ed;
    public depTable() {
        dd = new DepDao();
        ed = new EmpDao();
        this.addColumn(DEPCOL.ID.name());
        this.addColumn(DEPCOL.NAME.name());
        this.addColumn(DEPCOL.PEPCOUNT.name());

        List<Dep> deps = dd.loadLists();
        for (Dep dep : deps) {
            Vector data = new Vector();
            data.add(dep.getId());
            data.add(dep.getName());
            data.add(ed.loadByDepId(dep.getId()));
            this.addRow(data);
        }

    }

    @Override
    public void removeRow(int row) {
        int id = (int) getValueAt(row, DEPCOL.ID.getCode());
        int count = (int) getValueAt(row, DEPCOL.PEPCOUNT.getCode());
        Dep dep = dd.load(id);
        if (count > 0) {
            ManagerFrame.showMsg(null, dep.getName() + " has employee in it, can't be deleted");
            return;
        }
        int result = ManagerFrame.showConfim(null, "Are you sure to Delete " + dep.getName());
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        dd.delDep(id);
        super.removeRow(row);
    }

    public Dep data2Dep(int row) {
        int id = (int) getValueAt(row, DEPCOL.ID.getCode());
        Dep dep = dd.load(id);
        return dep;
    }


    public enum  DEPCOL {
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
