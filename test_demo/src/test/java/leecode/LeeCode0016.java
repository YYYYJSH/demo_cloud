package leecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeeCode0016 {

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length == 3){
            return Arrays.stream(nums).sum();
        }

        //排序
        nums = Arrays.stream(nums).sorted().toArray();

        int length = nums.length;
        //双指针？
        int l;
        int r ;

        List<Integer> res = new LinkedList<>();
        for (l = 0; l < nums.length; l++) {
//            if (l == length){
//                break;
//            }
            for (r = nums.length - 1; r > l ; r--) {
//                length--;
                for (int x = l+1; x < r; x++) {

                    int mid = nums[l] + nums[r] + nums[x];
                    if (res.size() != 0){
                        Integer in = res.get(res.size() - 1);

                        int inn = in - target;
                        int midn = mid - target;
                        if (Math.abs(inn) < Math.abs(midn) && res.size() >= length){
//                            return in;
                        }
                    }
                    res.add(mid);
                }

            }

        }

        List<Integer> collect = res.stream().sorted(Comparator.comparing(e -> {
            return Math.abs(e - target);
        })).collect(Collectors.toList());
        return collect.get(0);
    }

    public static void main(String[] args) {

        int[] param = {
                -49,-84,68,-30,30,-77,-15,-39,-98,-78,-96,13,10,14,-55,48,-13,-61,81,-77,9,85,-88,-86,-96,49,4,-34,83,67,85,-7,12,10,92,71,5,57,-11,-10,-72,65,-54,58,79,-6,-5,-93,14,44,56,-72,35,-87,4,-20,89,-85,15,-45,33,89,31,-89,15,-17,-12,31,-17,61,47,-29,98,-10,22,38,73,60,-39,82,-47,-58,-21,73,-72,25,-46,88,34,54,-19,-78,-84,-94,-18,-9,-7,-56,88,99,61,-10,-43,-83,62,-67,95,-4,-14,100,5,29,7,73,-46,20,60,81,95,-13,-32,69,56,-4,-2,68,79,-53,-14,81,-63,100,-97,-59,-9,12,84,0,19,76,8,63,-39,-38,-7,45,-51,-60,91,4,22,-74,-64,77,45,38,-95,-72,82,-52,-27,26,-74,-92,-70,97,13,-96,-77,-26,57,6,30,50,-19,68
        };

        LeeCode0016 leeCode0016 = new LeeCode0016();
        int res = leeCode0016.threeSumClosests(param,30);
        System.out.println(res);
    }



    public int threeSumClosests(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = len - 1;
            while (start < end) {
                int value = nums[i] + nums[start] + nums[end];
                if (value == target) {
                    return value;
                }
                if (Math.abs(value - target) < min) {
                    min = Math.abs(value - target);
                    ans = value;
                }
                if (value > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return ans;
    }

}
