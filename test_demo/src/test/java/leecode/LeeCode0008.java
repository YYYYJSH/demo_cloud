package leecode;

public class LeeCode0008 {

    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        //1.去空格
        int index = 0;
        while (index < len && chars[index] == ' ')
            index++;
        //2.排除极端情况 "    "
        if (index == len) return 0;
        //3.设置符号
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '-') {
            index++;
            sign = -1;
        } else if (firstChar == '+') {
            index++;
        }
        int res = 0, last = 0; //last 记录上一次的res，以此来判断是否溢出
        while (index < len) {
            char c = chars[index];
            if (c < '0' || c > '9') break;
            int tem = c - '0';
            last = res;
            res = res * 10 + tem;
            if (last != res / 10)  ////如果不相等就是溢出了
                return (sign == (-1)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            index++;
        }
        return res * sign;
    }

    public static void main(String[] args) {
        LeeCode0008 solution = new LeeCode0008();
        String str = "2147483646";
        int res = solution.myAtoi(str);
        System.out.println(res);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }
}
