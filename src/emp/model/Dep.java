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
        return name;
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Dep other = (Dep) obj;
        if (this.getId() == other.getId()) {
            return true;
        }
        return false;
    }
}
