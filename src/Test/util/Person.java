package Test.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;


public class Person implements Serializable, Comparable<Person> {
    private int    id;
    private String name;
    private String gender;
    private int    age;
    private double salary;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String aGender) {
        gender = aGender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public Person(int aId, String aName) {
        id = aId;
        name = aName;
    }

    public Person(int aId, String aName, String aGender, int aAge, double aSalary) {
        id = aId;
        name = aName;
        gender = aGender;
        age = aAge;
        salary = aSalary;

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
