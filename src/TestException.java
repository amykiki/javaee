import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Amysue on 2016/1/8.
 */
public class TestException {

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("argument nums should be greate than 2");
        }
        int sum;
        try {
            sum = Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
            System.out.println(args[0] + " + " + args[1] + " = " + sum);
        } catch (NumberFormatException e) {
            System.out.println("请输入正确的参数");
        }

        TestException exObj = new TestException();
        int n = exObj.divide(15, 4);
        System.out.println(n);
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter n");
//        int i = in.nextInt();
//        stackTrace.factorial(i);


//        exObj.printError();
    }

    public int divide(int a, int b) {
        int result = -1;
        try {
            result = a / b;
            System.out.println("test....");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("input is 0, Wrong");
            ;
        }

        return result;
    }

    public void printError() {
        System.out.println("this is a test function");
//        throw new ArithmeticException("this is my own exception");
        try {
            throw new SerletException("this is database error");
        } catch (SerletException e) {
            Throwable se = new FileFormatException("this is file format error");
            se.initCause(e);
            throw e;
        }
    }


}

class FileFormatException extends IOException {
    public FileFormatException() {
        super();
    }

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileFormatException(Throwable cause) {
        super(cause);
    }
}

class SerletException extends RuntimeException {
    public SerletException() {
        super();
    }

    public SerletException(String message) {
        super(message);
    }

    public SerletException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerletException(Throwable cause) {
        super(cause);
    }
}