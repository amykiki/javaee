package emp.test;

import emp.dao.DaoFactory;
import emp.dao.EmpDao;
import emp.dao.IEmpDao;
import emp.model.Emp;
import emp.util.Gender;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/2/17.
 */
public class EmpDaoTest {
//    EmpDao ed = new EmpDao();

    IEmpDao ed = DaoFactory.getEmpDao();

    @Test
    public void testAddEmp() throws Exception {
        Emp emp = null;
//        emp = new Emp("harry potter", Gender.MALE, 20000, 3);
//        emp = new Emp("Hermione Granger", Gender.FEMALE, 25000, 8);
//        Emp emp = new Emp("Draco Malfoy", Gender.MALE, 12000, 6);
        emp = new Emp("Ron Weasly", Gender.MALE, 5454.4, 3);
        System.out.println(ed.addEmp(emp));
    }

    @Test
    public void testDelEmp() throws Exception {
        ed.delEmp(8);
    }

    @Test
    public void testUpdateEmp() throws Exception {
        Emp emp = new Emp("Draco Malfoy", Gender.MALE, 12000, 4);
        emp.setId(3);
        ed.updateEmp(emp);
    }

    @Test
    public void testLoadEmp() throws Exception {
        Emp emp = ed.loadEmp(1);
        System.out.println(emp);
    }

    @Test
    public void testLoadList() throws Exception {
        List<Emp> emps = ed.loadList();

        for (Emp e : emps) {
            System.out.println(e);
        }
    }

    @Test
    public void testLoadByDepId() throws Exception {
        System.out.println("id = " + ed.loadByDepId(9));

    }

    @Test
    public void testMultiLoad() throws Exception {
        List<Emp> emps = ed.multiLoad(-1, "y");

        for (Emp e : emps) {
            System.out.println(e);
        }
    }
}