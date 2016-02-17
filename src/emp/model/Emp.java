package emp.model;

import emp.util.Gender;

/**
 * Created by Amysue on 2016/2/17.
 */
public class Emp {
    private int    id;
    private String name;
    private Gender gender;
    private double salary;
    private int    depid;

    public Emp(String name, Gender gender, double salary, int depid) {
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.depid = depid;
    }

    public Emp() {

    }

    public Emp(int id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", salary=" + salary +
                ", depid=" + depid +
                '}';
    }
}
