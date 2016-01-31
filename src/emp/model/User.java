package emp.model;

/**
 * Created by Amysue on 2016/1/30.
 */
public class User {
    private String name;
    private String password;
    private String nickname;
    private double salary;

    public User(String name, String password, String nickname, double salary) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.salary = salary;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User " + name + "[password:" + password + ", nickname:" + nickname + ", salary:" + salary + "]";
    }
}
