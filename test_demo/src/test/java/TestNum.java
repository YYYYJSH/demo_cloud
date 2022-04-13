import com.yjsh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.common.ConcurrentReaderHashMap;
import org.junit.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class TestNum {
    public static void main(String[] args) {

        System.out.println(8 + 7 + 8 + 9 + 11);

        User.UserBuilder builder = User.builder();
        User build = builder.build();
        List<User> collect = Stream.of(User.builder().id(1).build(),
                        User.builder().id(2).build(),
                        User.builder().id(3).build())
                .collect(Collectors.toList());
        collect.forEach(System.out::println);


        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
// 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        List<String> collect1 = strings.stream().filter(s -> s.isEmpty()).collect(Collectors.toList());
        System.out.println(count);
        System.out.println(collect1);


        List<String> stringss = Arrays.asList("abc", "", "bc", "efg", "abed", "", "jkl");
// 获取空字符串的数量
        long counts = strings.parallelStream().filter(String::isEmpty).count();
        System.out.println(counts);


        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream().forEach(num -> log.info(Thread.currentThread().getName() + ">>" + num));

    }


    @Test
    public void twoSum() {
        int[] nums = null;
        int target = 0;
        int[] out = null;

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        Object[] objects = integerStream.toArray();


        System.out.println();


        double a = 1000.16651;
        double v = new BigDecimal(String.valueOf(a)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(v);
        Random rand = new Random();

        //int randNumber =rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        int one = rand.nextInt(10 + 1); // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        System.out.println(" 1 : " + one);
        if (one == 10){
            int two = rand.nextInt(10 + 1); // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
            System.out.println(" 2: " + two);
            if (two == 10){
                int three = rand.nextInt(10 + 1); // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
                System.out.println(" 3: " + three);
                if (three == 10){
                    int four = rand.nextInt(10 + 1); // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
                    System.out.println(" 4: " + four);
                }
            }
        }


    }

    @Test
    public void add(){

        int i = 10;
        switch (i){
            case 1:
                System.out.println("this is 1");
            case 2:
                System.out.println("this is 2");
            case 10:
                System.out.println("this is 10");
                break;
            default:
                System.out.println("default");
        }

        System.out.println("aaaa");
    }

//    public int[] twoSum(int[] nums, int target) {
//
//
//    }

    @Test
    public void testName() throws ClassNotFoundException {
        Thread thread = Thread.currentThread();

        System.out.println(thread.getName());
//        Class<?> classc = Class.forName(name);
//        String name1 = classc.getName();
//        Method[] methods = classc.getMethods();
    }
}
