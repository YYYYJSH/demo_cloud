package leecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 */
public class LeeCode0014 {
    public String longestCommonPrefix(String[] strs) {

        List<String> list = Arrays.stream(strs).collect(Collectors.toList());
        String  res = "";
        List<Integer> collect = list.stream().map(String::length).collect(Collectors.toList());
        collect = collect.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        Integer minLength = collect.get(0);
        for (Integer integer = 0; integer < minLength; integer++) {
            char c = list.get(0).charAt(integer);
            Integer finalInteger = integer;
            boolean b = list.parallelStream().allMatch(e -> {
                char d = e.charAt(finalInteger);
                return d == c;
            });
            if (b){
                res = res + String.valueOf(c);
            }else {
                break;
            }
        }
        return res;
    }
}
