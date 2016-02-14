package emp.model;

/**
 * Created by Amysue on 2016/2/14.
 */
public class Dep {
    private int id;
    private String name;

    public Dep(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dep(String name) {
        this.name = name;
    }

    public Dep() {
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

    @Override
    public String toString() {
        return "Dep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
