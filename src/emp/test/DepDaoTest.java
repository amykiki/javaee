package emp.test;

import emp.dao.DepDao;
import emp.model.Dep;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/2/14.
 */
public class DepDaoTest {
    private DepDao dd = new DepDao();

    @Test
    public void testLoadById() throws Exception {
        Dep dep = dd.loadById(3);
        System.out.println(dep);
    }

    @Test
    public void testLoadByName() throws Exception {
        Dep dep = dd.loadByName("Gryffindor");
        System.out.println(dep);
    }
}