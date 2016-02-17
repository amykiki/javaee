package emp.test;

import emp.dao.EmpDao;
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
    EmpDao ed = new EmpDao();

    @Ignore
    @Test
    public void testAddEmp() throws Exception {
//        Emp emp = new Emp("harry potter", Gender.MALE, 20000, 2);
//        Emp emp = new Emp("Hermione Granger", Gender.FEMALE, 25000, 2);
        Emp emp = new Emp("Draco Malfoy", Gender.MALE, 12000, 4);
        ed.addEmp(emp);
    }

    @Ignore
    @Test
    public void testDelEmp() throws Exception {
        ed.delEmp(2);
    }

    @Ignore
    @Test
    public void testUpdateEmp() throws Exception {
        Emp emp = new Emp("Draco Malfoy", Gender.MALE, 12000, 4);
        emp.setId(3);
        ed.updateEmp(emp);
    }

    @Ignore
    @Test
    public void testLoadEmp() throws Exception {
        Emp emp = ed.loadEmp(1);
        System.out.println(emp);
    }

    @Ignore
    @Test
    public void testLoadList() throws Exception {
        List<Emp> emps = ed.loadList();
//        if (emps != null) {

            for (Emp e : emps) {
                System.out.println(e);
            }
//        }
    }

    @Test
    public void testLoadByDepId() throws Exception {
        System.out.println("id = " + ed.loadByDepId(9));

    }
}