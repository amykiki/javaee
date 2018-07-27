package effectivejava.item11;

/**
 * @author :  ZouShumin
 * @Project Name :  javaee
 * @Package Name :  effectivejava.common
 * @Description :
 * @Creation Date:  2018-07-26 15:20
 * --------  ---------  --------------------------
 */
public class PhoneNumber implements Cloneable{
    private int areaCode;
    private int prefix;
    private int lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = areaCode;
        this.prefix =  prefix;
        this.lineNumber =  lineNumber;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    /**
     * 类中field只有primitive和不变对象的时候，clone方法直接这样调用就可以了
     */
    public PhoneNumber clone() {
        try {
            return (PhoneNumber)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode=" + areaCode +
                ", prefix=" + prefix +
                ", lineNumber=" + lineNumber +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof PhoneNumber)) {
            return true;
        }
        PhoneNumber pn = (PhoneNumber) obj;

        return pn.areaCode == areaCode && pn.prefix == prefix && pn.lineNumber == lineNumber;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31* result + prefix;
        result = 31 * result + lineNumber;
        return  result;
    }


}
