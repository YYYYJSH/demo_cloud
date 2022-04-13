package leecode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeeCode0012 {
    public String intToRoman(int num) {
        Map<Integer, String> map = new LinkedHashMap(){{
            put(1000, "M");
            put(900, "CM");
            put(500, "D");
            put(400, "CD");
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }};

        StringBuilder stringBuilder = new StringBuilder();
        while(num > 0){
            for (Map.Entry<Integer, String> entry:map.entrySet()) {
                if(entry.getKey() <= num){
                    num -= entry.getKey();
                    stringBuilder.append(entry.getValue());
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LeeCode0012 leeCode0012 = new LeeCode0012();
        String s = leeCode0012.intToRoman(1515);
        System.out.println(s);


    }
}
