import java.util.Arrays;

/**
 * Created by Amysue on 2016/1/6.
 */
public class TestArray {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 23, 11, 9};
        int[] nums2 = {33, 1, 4, 7, 0};
        System.out.println(Arrays.toString(nums2));
        for (int i: nums) System.out.println(i);

        int[] nums3 = new int[50];
        for (int i = 0; i < nums3.length; i++) {
            nums3[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(nums3));
    }
}
