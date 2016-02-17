package emp.test;

import emp.dao.DepDao;
import emp.model.Dep;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/2/14.
 */
public class DepDaoTest {
    private DepDao dd = new DepDao();

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

//    @Ignore
    @Test
    public void testAddDep() throws Exception {
//        Dep dep = new Dep("Hogwarts");
//        Dep dep = new Dep("Gryffindor");
//        Dep dep = new Dep("Slytherin");
//        Dep dep = new Dep("Hufflepuff");
//        Dep dep = new Dep("Ravenclaw");
//        Dep dep = new Dep("Forbidden Forest");
//        Dep dep = new Dep("Forbidden Forest");
//        Dep dep = new Dep("Minister");
        Dep dep = new Dep("burrow");
        assertTrue(dd.addDep(dep) > 0);
    }

    @Ignore
    @Test
    public void testUpdateDep() throws Exception {
        Dep dep = new Dep(7, "Hogwarts");
        assertTrue(dd.updateDep(dep));
    }

    @Ignore
    @Test
    public void testDelDep() throws Exception {
        assertTrue(dd.delDep(1));
    }

    @Ignore
    @Test
    public void testLoad() throws Exception {
        System.out.println(dd.load(3));
    }

    @Ignore
    @Test
    public void testLoadLists() throws Exception {
        List<Dep> deps = dd.loadLists();
        System.out.println(deps.size());
        for (Dep d : deps) {
            System.out.println(d);
        }
    }
}