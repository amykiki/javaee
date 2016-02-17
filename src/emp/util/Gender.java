package emp.util;

/**
 * Created by Amysue on 2016/2/17.
 */
public enum Gender {
    MALE(0), FEMALE(1);
    private int code;
    private Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
