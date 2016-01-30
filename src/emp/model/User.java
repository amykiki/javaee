package emp.model;

/**
 * Created by Amysue on 2016/1/30.
 */
public class User {
    private String name;
    private String password;
    private String nickname;

    public User(String name, String password, String nickname) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
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
}
