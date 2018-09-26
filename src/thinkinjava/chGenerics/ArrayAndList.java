package thinkinjava.chGenerics;

import java.util.ArrayList;
import java.util.List;

public class ArrayAndList {
    public static void main(String[] args) {
        Object[] objArray = new Long[1];
        //compile success, but runtime throw java.lang.ArrayStoreException
        objArray[0] = "I don't fit in";

        //won't compile, Incompatible types, generics is invariant
//        List<Object> o1 = new ArrayList<Long>();
//        o1.add("I don't fit in");

        //编译报错，generic array creation
        //List<String>[] lArr = new ArrayList<String>[10];
        //唯一合法的泛型参数类型是无限制通配符类型(unbounded wildcard tppe，不怎么使用
        List<?>[] lArry = new ArrayList<?>[10];

    }
}
