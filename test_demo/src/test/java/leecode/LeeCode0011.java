package leecode;

public class LeeCode0011 {

    public int maxArea(int[] height) {
        if(height.length<2) return 0;
        int left = 0;
        int right = height.length-1;
        // ans 为结果 min为两个木板的长度最小值
        int ans = 0;
        int min = 0;
        while(left<right){
            min = Math.min(height[left],height[right]);
            ans = Math.max(ans,(right-left)*min);
            System.out.println(height[left] + " --- " + height[right]+ " ==== " + ans);
            // 如果左边短 就右移
            if(height[left]<height[right]){
                left++;
            }else{
                // 右边短就左移
                right --;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LeeCode0011 leeCode0011 = new LeeCode0011();
        int[] hii = {1,2,3,4,5,6};
        int i = leeCode0011.maxArea(hii);
        System.out.println(i);
    }

}
