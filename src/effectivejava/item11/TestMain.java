package effectivejava.item11;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  effectivejava.item11
 * @Description :
 * @Creation Date:  2018-07-26 15:25
 * --------  ---------  --------------------------
 */
public class TestMain {
    /**
     * 测试PhoneNumber的clone方法
     * PhoneNumber的所有属性都是primitive，所以clone方法简单调用super.clone即可
     * 测试效果
     */
    public static void test1() {
        PhoneNumber pn1 = new PhoneNumber(123, 456, 7890);
        PhoneNumber pn2 = pn1.clone();
        debugPrint(pn1, pn2);
        pn2.setAreaCode(111);
        pn2.setPrefix(222);
        debugPrint(pn1, pn2);
    }

    public static void debugPrint(Object pn1, Object pn2) {
        System.out.println("=============DEBUG START================");
        System.out.println("pn1 = " + pn1);
        System.out.println("pn2 = " + pn2);
        System.out.println("pn1.hashCode() = " + pn1.hashCode());
        System.out.println("pn2.hashCode() = " + pn2.hashCode());
        System.out.println("System.identityHashCode(pn1) = " + System.identityHashCode(pn1));
        System.out.println("System.identityHashCode(pn2) = " + System.identityHashCode(pn2));
        System.out.println("pn1.equals(pn2) = " + pn1.equals(pn2));
        System.out.println("pn1 == pn2: " + (pn1 == pn2));
        System.out.println("=============DEBUG END================");
    }

    public static void testStack() {
        Stack s1 = new Stack();
        s1.setPnum(new PhoneNumber(989, 989, 7112));
        s1.push(new PhoneNumber(111, 222, 3333));
        s1.push(new PhoneNumber(444, 555, 6666));
        Stack s2 = s1.clone();
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        s2.getPnum().setAreaCode(111);
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        s2.push(new PhoneNumber(777, 888, 9999));
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
    }

    public static void testHashTable() {
        Hashtable<Integer, PhoneNumber> tb = new Hashtable<>();
        tb.put(1, new PhoneNumber(111, 222, 3333));
        tb.put(2, new PhoneNumber(444, 555, 6666));
        tb.put(3, new PhoneNumber(777, 888, 9999));
        Hashtable<Integer, PhoneNumber> ctb = (Hashtable<Integer, PhoneNumber>) tb.clone();
        System.out.println(tb);
        System.out.println(ctb);
        ctb.get(1).setAreaCode(123);
        ctb.get(1).setLineNumber(8888);
        System.out.println(tb);
        System.out.println(ctb);
    }

    public static void testHashMap() {
        HashMap<Integer, PhoneNumber> map = new HashMap<>();
        map.put(1, new PhoneNumber(111, 222, 3333));
        map.put(2, new PhoneNumber(444, 555, 6666));
        map.put(3, new PhoneNumber(777, 888, 9999));
        HashMap<Integer, PhoneNumber> cmap = (HashMap<Integer, PhoneNumber>) map.clone();
        System.out.println(map);
        System.out.println(cmap);
        cmap.get(1).setAreaCode(123);
        cmap.get(1).setLineNumber(8888);
        cmap.put(2, new PhoneNumber(123, 123, 1122));
        System.out.println(map);
        System.out.println(cmap);
    }
    public static void main(String[] args) {
//        test1();
//        testStack();
//        testHashTable();
        testHashMap();
    }
}
