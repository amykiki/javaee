package emp.view;

import emp.dao.DaoFactory;
import emp.dao.DepDao;
import emp.dao.IDepDao;
import emp.model.Dep;

import javax.swing.*;
import java.util.List;

/**
 * Created by Amysue on 2016/2/17.
 */
public class DepCombo extends DefaultComboBoxModel<Dep> {
    private IDepDao dd;

    public DepCombo(int allDep) {
        dd = DaoFactory.getDepDao();
        if (allDep == 1) {
            this.addElement(new Dep(-1, "All"));
        }
        List<Dep> deps = dd.loadLists();
        for (Dep dep : deps) {
            this.addElement(dep);
        }
    }

    public Dep selectDep(int depId) {
        Dep dep = dd.load(depId);
        setSelectedItem(dep);
        return dep;
    }

}
