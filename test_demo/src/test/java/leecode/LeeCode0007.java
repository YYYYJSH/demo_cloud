package leecode;

public class LeeCode0007 {

    public static int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }

    public static void main(String[] args) {

        int reverse = reverse(13320);
        System.out.println(reverse);
    }
}
