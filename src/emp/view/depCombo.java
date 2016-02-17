package emp.view;

import emp.dao.DepDao;
import emp.model.Dep;

import javax.swing.*;
import java.util.List;

/**
 * Created by Amysue on 2016/2/17.
 */
public class depCombo extends DefaultComboBoxModel<Dep> {
    private DepDao dd;

    public depCombo(int allDep) {
        dd = new DepDao();
        if (allDep == 1) {
            this.addElement(new Dep(-1, "All"));
        }
        List<Dep> deps = dd.loadLists();
        for (Dep dep : deps) {
            this.addElement(dep);
        }
    }

}
