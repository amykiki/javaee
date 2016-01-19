package Test.Enums;

import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Amysue on 2016/1/19.
 */
public class JavaEnumExamples {
    public static void main(String[] args) throws IOException {
        UsingEnumMethods();
        UsingEnumValueOf();
        UsingEnumValues();
        UsingEnumInSwitch(ThreadStatesEnum.START);
        UsingEnumInSwitch(ThreadStatesEnum.DEAD);
        UsingEnumMap();
        UsingEnumSet();
    }

    private static void UsingEnumSet() {
        EnumSet<ThreadStatesEnum> enumSet = EnumSet.allOf(ThreadStatesEnum.class);
        for (ThreadStatesEnum tsenum : enumSet) {
            System.out.println("Using Enumset, priority = " + tsenum.getPriority());
        }
    }

    private static void UsingEnumMap() {
        EnumMap<ThreadStates, String> enumMap = new EnumMap<ThreadStates, String>(ThreadStates.class);
        enumMap.put(ThreadStates.START, "Thread is started");
        enumMap.put(ThreadStates.RUNNING, "Thread is running");
        enumMap.put(ThreadStates.WAITING, "Thread is waiting");
        enumMap.put(ThreadStates.DEAD, "Thread is dead");

        Set<ThreadStates> keySet = enumMap.keySet();
        for (ThreadStates key : keySet) {
            System.out.println("key = " + key.toString() + ":: value=" + enumMap.get(key));
        }
    }
    private static void UsingEnumInSwitch(ThreadStatesEnum th) {
        switch (th) {
            case START:
                System.out.println("Start Thread");
                break;
            case RUNNING:
                System.out.println("Running Thread");
                break;
            case WAITING:
                System.out.println("Waiting Thread");
                break;
            case DEAD:
                System.out.println("Dead Thread");
        }
    }
    private static void UsingEnumValues() {
        ThreadStatesEnum[] thArray = ThreadStatesEnum.values();

        for (ThreadStatesEnum th : thArray) {
            System.out.println(th.toString() + "::priority =" + th.getPriority());
        }
    }
    private static void UsingEnumValueOf() {
        ThreadStatesEnum th = Enum.valueOf(ThreadStatesEnum.class, "START");
        System.out.println("th priority is=" + th.getPriority());
    }
    private static void UsingEnumMethods() throws IOException {
        ThreadStatesEnum thc = ThreadStatesEnum.DEAD;
        System.out.println("priority is:" + thc.getPriority());

        thc = ThreadStatesEnum.DEAD;
        System.out.println("using overiden method." + thc.toString());

        thc = ThreadStatesEnum.START;
        System.out.println("using overiden method." + thc.toString());
        thc.setPriority(10);
        System.out.println("Enum Constant variable changed priority value=" + thc.getPriority());
        thc.close();

    }
}
