package leecode;

public class LeeCode0010 {

    public boolean isMatch(String s, String p) {
        return s.matches(p);
    }


    public static void main(String[] args) {
        LeeCode0010 leeCode0009 = new LeeCode0010();
        boolean match = leeCode0009.isMatch("aa","a");
        System.out.println(match);
    }
}
