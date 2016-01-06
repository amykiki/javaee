/**
 * Created by Amysue on 2016/1/5.
 */
public class TestObj {

    public static void main(String[] args) {
        Person p1 = new Person(1, "amy");
        Person p2 = new Person(2, "kevin");
        System.out.println(p1.getClass());
        System.out.println(p1.getClass().getName());

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
       
    }

}

class Person {
    int id;
    String name;

    public Person (int aId, String aName) {
        id = aId;
        name = aName;
    }

}
