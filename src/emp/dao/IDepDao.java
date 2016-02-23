package emp.dao;

import emp.model.Dep;
import emp.model.EmpException;

import java.util.List;

/**
 * Created by Amysue on 2016/2/22.
 */
public interface IDepDao {
    public int addDep(Dep dep) throws EmpException;
    public boolean delDep(int id) throws EmpException;
    public boolean updateDep(Dep dep) throws EmpException;
    public Dep load(int id);
    public Dep load(String name);
    public List<Dep> loadLists();

}
