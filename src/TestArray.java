import java.util.*;

/**
 * Created by Amysue on 2016/1/6.
 */
public class TestArray {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 23, 11, 9};
        int[] nums2 = new int[10];
        int size = 7;
        for (int i = 0; i < size; i++) {
            nums2[i] = i + 1;
        }
        size--;
        for (int i = 1; i < size; i++) {
            nums2[i] = nums2[i+1];
        }
        nums2[size] = 0;
        System.out.println(Arrays.toString(nums2));

        for (int i = size; i > 1; i--) {
            nums2[i] = nums2[i-1];
        }
        nums2[1] = 2;
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums2));
        String[] str1 = {"amy", "kevin", "carl", "jay"};

        List<String> list1 = Arrays.asList(str1);
        System.out.println(list1.getClass());
        ListIterator<String> liter1 = list1.listIterator();
        System.out.println(liter1.next());
        System.out.println(liter1.next());
        System.out.println(liter1.next());
        while (liter1.hasPrevious()) {
            System.out.println(liter1.previous());
        }

        List<String> a = new LinkedList<>();
        a.add("Bob");
        a.add("Boug");
        a.add("Frances");
        a.add("Gloria");

        List<String> group2 = a.subList(1, 3);
        System.out.println(group2.getClass());
        for (String str : group2) System.out.println(str);
        group2.add("German");

        List<String> b = new LinkedList<>(list1);
        b.add(1, "bruce");

        for (String str : list1) System.out.println(str);
        System.out.println("======begin==================");
        for (String str : a) System.out.println(str);
        System.out.println("-----------------------------");
        for (String str : b) System.out.println(str);
        System.out.println("======end==================");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);
        System.out.println(b);

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println(b);

        PriorityQueue<String> pq = new PriorityQueue<>(a);
        System.out.println(pq);
        for (String str : pq) {
//            System.out.println(str);
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }

    }
}
