package effectivejava.item11;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  effectivejava.item11
 * @Description :
 * @Creation Date:  2018-07-26 16:48
 * --------  ---------  --------------------------
 */
public class Stack implements Cloneable {
    private PhoneNumber[] pns;
    private PhoneNumber pnum;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.pns = new PhoneNumber[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(PhoneNumber p) {
        ensureCapacity();
        pns[size++] = p;
    }

    public PhoneNumber[] getPns() {
        return pns;
    }

    public PhoneNumber getPnum() {
        return pnum;
    }

    public int getSize() {
        return size;
    }

    public PhoneNumber pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        PhoneNumber p = pns[--size];
        pns[size] = null;
        return p;
    }

    public void setPnum(PhoneNumber pnum) {
        this.pnum = pnum;
    }

    @Override
    public Stack clone() {
        try {
            Stack s = (Stack) super.clone();
            s.pnum = pnum.clone();
            s.pns = pns.clone();
            return s;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Stack{" +
                "pnum=" + pnum +
                ", size=" + size +
                ", pns=" + ArraytoString(pns) +
                '}';
    }

    private String ArraytoString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            if (a[i] == null) {
                break;
            }
            b.append(String.valueOf(a[i]));
            b.append(", ");
        }
        return b.append(']').toString();
    }
    private void ensureCapacity() {
        if (pns.length == size) {
            pns = Arrays.copyOf(pns, 2 * size + 1);
        }
    }


}
