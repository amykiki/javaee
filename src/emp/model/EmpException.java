package emp.model;

/**
 * Created by Amysue on 2016/1/30.
 */
public class EmpException extends Exception {
    public EmpException() {
        super();
    }

    public EmpException(String message) {
        super(message);
    }

    public EmpException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmpException(Throwable cause) {
        super(cause);
    }

    protected EmpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
