package leecode;

public class LeeCode0006 {
    public static String convert(String s, int numRows) {
        char[][] strings = new char[numRows][s.length()];
        int row =strings.length;
        int col =strings[0].length;
        int i=0;
        int j=0;
        int index=0;
        if(numRows==1) return s;
        while(index<s.length()){
            while(i<row&&index<s.length()){
                strings[i++][j]=s.charAt(index++);
            }
            if(i==row){
                i-=2;
                j++;
                while(i>0&&index<s.length()){
                    strings[i--][j++]=s.charAt(index++);
                }
            }
        }
        String str="";
        for(int a=0;a<row;a++){
            for(int b=0;b<col;b++){
                if(strings[a][b] != 0){
                    str=str+""+strings[a][b];
                }
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String x = "PAYPALISHIRING";
        String convert = convert(x, 3);
        System.out.println(convert);
    }
}
