package leecode;

import java.util.*;
import java.util.stream.Collectors;

public class LeeCode0004 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {


        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());

        list1 = new LinkedList<Integer>(list1);

        list1.addAll(list2);
        list1 = list1.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        int size = list1.size();
        double x = 0;
        boolean b = size % 2 == 1;
        int mid = size / 2;
        if (size == 1 ){
            return list1.get(0);
        }
        if (b){
            x = list1.get(mid);
            System.out.println(list1);
            System.out.println(mid+1);
            System.out.println("xxxx:" + x);
        }else {
            int i = list1.get(mid-1);
            int is = list1.get(mid);
            x = (i+is*1.0)/2;
        }
        return x;
    }

    public static void main(String[] args) {
        int[] num1 = {1,3};
        int[] num2 = {2};
        double medianSortedArrays = findMedianSortedArrays(num1, num2);

        System.out.println(medianSortedArrays);
    }
}
