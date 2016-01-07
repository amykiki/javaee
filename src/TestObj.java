import java.util.ArrayList;
import java.util.Objects;

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

        String s1 = new String("123");
        String s2 = "123";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        Person p3 = new Person(3, "S");
        Person p4 = new Person(3, "S");

        System.out.println(p3 == p4);
        System.out.println(p3.equals(p4));
        System.out.println(p3.equals("amy"));

        System.out.println(p3.hashCode());
        System.out.println(p4.hashCode());

        Book[] books = new Book[4];
        books[0] = new Book("learn data structure");
        books[1] = new Book("java");
        books[2] = new Book("html");
        books[3] = new Book("jsp");
        Person p5 = new Person(5, "Eric", books);
        p5.readBook();
    }

}

class Person {
    int id;
    String name;
    Book[] books;

    public Person (int aId, String aName) {
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
        return ( pObj.id == this.id && pObj.name.equals(name));
    }

    @Override
    public int hashCode() {
        return 11 * new Integer(id) + 7 * Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "id = " + id + ", name:" + name;
    }

    public void readBook() {
        System.out.println(this.toString() + " is reading:");
        for(Book b: this.books) System.out.println(b);
    }
}

class Book {
    String b;

    public Book(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return b.toString();
    }
}
