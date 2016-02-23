package emp.dao;

import emp.model.Emp;
import emp.model.EmpException;

import java.util.List;

/**
 * Created by Amysue on 2016/2/22.
 */
public interface IEmpDao {

    public int addEmp(Emp emp) throws EmpException;
    public boolean delEmp(int id) throws EmpException;
    public boolean updateEmp(Emp emp) throws EmpException;
    public Emp loadEmp(int id) throws EmpException;
    public List<Emp> loadList();
    public List<Emp> multiLoad(int depid, String name);
    public int loadByDepId(int depid);
}
