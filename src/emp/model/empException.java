package emp.model;

/**
 * Created by Amysue on 2016/1/30.
 */
public class empException extends RuntimeException {
    public empException() {
        super();
    }

    public empException(String message) {
        super(message);
    }

    public empException(String message, Throwable cause) {
        super(message, cause);
    }

    public empException(Throwable cause) {
        super(cause);
    }

    protected empException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
