package leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeeCode0001 {


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 7, 20, 22};
        Integer target = 9;
        int[] ints = twoSum(nums, target);
        Arrays.stream(ints).forEach(System.out::println);
    }

}
