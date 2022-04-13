package leecode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeeCode0009 {
    public boolean isPalindrome(int x) {

        Integer integer = x;
        String s = integer.toString();
        String[] split = s.split("");

        List<String> list = Arrays.stream(split).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            String s1 = list.get(i);
            String s2 = list.get(list.size() - i - 1);
            if (!s1.equals(s2)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeeCode0009 leeCode0009 = new LeeCode0009();
        boolean palindrome = leeCode0009.isPalindrome(19201291);
        System.out.println(palindrome);
    }
}
