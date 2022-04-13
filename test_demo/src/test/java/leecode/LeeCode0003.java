package leecode;



public class LeeCode0003 {


    public static int lengthOfLongestSubstring(String string) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
//        for(int i = 0; i < 128; i++) {
//            last[i] = -1;
//        }
        int n = string.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = string.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    public static void main(String[] args) {
        String test = "pwwkewaszx";
        int i = lengthOfLongestSubstring(test);
        System.out.println(i);
        int q = xx(test);
        System.out.println(q);


//        String[] tests = test.split("");
//        System.out.println(tests);
    }

    public static int xx(String strs){
        char [] chars = strs.toCharArray();
        StringBuilder str = new StringBuilder();
        int hisLength = 0;
        for (char aChar : chars) {
            if (str.indexOf(aChar + "") != -1) {
                hisLength = Math.max(hisLength, str.length()); //记录历史无重复字符串的长度最大值
                //如果剩余字符串可判断个数已小于历史长度，则退出判断
                if (chars.length - str.indexOf(aChar + "") - 1 < hisLength) {
                    break;
                }
                str.delete(0, str.indexOf(aChar + "") + 1); //删除0-该位置字符
            }
            str.append(aChar);
        }
        return Math.max(hisLength, str.length()); //防止字符串长度为1
    }


}
