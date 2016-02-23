package emp.test;

import emp.dao.DaoFactory;
import emp.dao.IDepDao;
import emp.model.Dep;
import org.junit.Test;

import java.nio.channels.Pipe;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Amysue on 2016/2/14.
 */
public class DepDaoTest {
//    private DepDao dd = new DepDao();
    private IDepDao dd = DaoFactory.getDepDao();

//    @Test
//    public void testLoadById() throws Exception {
//        Dep dep = dd.loadById(3);
//        System.out.println(dep);
//    }
//
//    @Test
//    public void testLoadByName() throws Exception {
//        Dep dep = dd.loadByName("Gryffindor");
//        System.out.println(dep);
//    }

    @Test
    public void testAddDep() throws Exception {
//        Dep dep = new Dep("Hogwarts");
//        Dep dep = new Dep("Gryffindor");
//        Dep dep = new Dep("Slytherin");
//        Dep dep = new Dep("Hufflepuff");
        Dep dep = new Dep("Ravenclaw");
//        Dep dep = new Dep("Forbidden Forest");
//        Dep dep = new Dep("Forbidden Forest");
//        Dep dep = new Dep("Minister");
//        Dep dep = new Dep("burrow");
        assertTrue(dd.addDep(dep) > 0);
    }

    @Test
    public void testUpdateDep() throws Exception {
        Dep dep = new Dep(6, "Slytherin");
        assertTrue(dd.updateDep(dep));
    }

    @Test
    public void testDelDep() throws Exception {
        assertTrue(dd.delDep(5));
    }

    @Test
    public void testLoad() throws Exception {
        System.out.println(dd.load(9));
        System.out.println(dd.load("Hogwarts3"));
    }

    @Test
    public void testLoadLists() throws Exception {
        List<Dep> deps = dd.loadLists();
        System.out.println(deps.size());
        for (Dep d : deps) {
            System.out.println(d);
        }
    }
}