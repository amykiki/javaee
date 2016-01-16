import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;


public class Person implements Serializable, Comparable<Person>{
    private int id;
    private String name;
    private Book[] books;

    public Person(int aId, String aName) {
        id = aId;
        name = aName;
    }

    public Person(int id, String name, Book[] books) {
        this(id, name);
        this.books = books;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Person pObj = (Person) obj;
        return (pObj.id == this.id && pObj.name.equals(name));
    }

    @Override
    public int hashCode() {
        return (11 * id) + (7 * Objects.hashCode(name));
    }

    @Override
    public String toString() {
        return "id = " + id + ", name:" + name;
    }

    public void readBook() {
        System.out.println(this.toString() + " is reading:");
        for (Book b : this.books) System.out.println(b);
    }

    @Override
    public int compareTo(Person pObj) {
        int result = this.id - pObj.id;
        if (result == 0) {
            return this.name.compareTo(pObj.name);
        } else {
            return result;
        }
    }

    public static void printPersons(Collection<Person> collection) {
        System.out.println("-------------------------------------");
        for (Person p : collection) {
            System.out.println(p);
        }
    }

    public static Comparator<Person> NameComparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.name.compareTo(o2.name);
        }
    };

}
