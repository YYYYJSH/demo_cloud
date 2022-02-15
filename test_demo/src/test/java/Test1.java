public class Test1 {
    public static void main(String[] args) {

        //一个参数无返回
        NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("NoReturnOneParam param:" + a);
            return a+1;
        };


        System.out.println("xxx");
        int method = noReturnOneParam.method(6);
        System.out.println(method);

    }
}