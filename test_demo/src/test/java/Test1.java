import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {

        //一个参数无返回
        NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("NoReturnOneParam param:" + a);
            return a+1;
        };


        System.out.println("xxx");
        int method = noReturnOneParam.method(6);
        System.out.println(method);


        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());

        Map map = new ConcurrentHashMap();

        Optional<Integer> optionalnull  = Optional.empty();

        Optional<Integer> optionalInteger = Optional.of(12);
        System.out.println(optionalnull.isPresent());
        System.out.println(optionalInteger.isPresent());

        String date = LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATE_PATTERN);
        LocalDateTime now = LocalDateTime.now().plusDays(38);

        LocalDateTime parse = LocalDateTimeUtil.parse("2022-05-06 00:01:02",DatePattern.NORM_DATETIME_PATTERN);

        LocalDateTime localDateTime = LocalDateTime.now().minusDays(2);
        boolean before = now.isBefore(parse);
        System.out.println(before);
        String dates = LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATE_PATTERN);
        System.out.println(dates);


        System.out.println("==============");

        LocalDateTime startDate = LocalDateTimeUtil.parse("2022-03-07",DatePattern.NORM_DATE_PATTERN);
        LocalDateTime endDate = LocalDateTimeUtil.parse("2022-04-07" + " 23:59:59",DatePattern.NORM_DATETIME_PATTERN);

        for (int i = 0; i < 60; i++) {
            LocalDateTime nows = LocalDateTime.now().plusDays(i);
            if (!nows.isAfter(startDate) || !nows.isBefore(endDate)){
                System.out.println("out     " + nows);
            }else {
                System.out.println("in      " + nows);
            }
        }

        System.out.println("==============");

        BigDecimal big = new BigDecimal("10.00");
        BigDecimal small = new BigDecimal("0.00");

        System.out.println(big.compareTo(small));
        System.out.println(small.compareTo(small));
        System.out.println(small.compareTo(big));


//        System.out.println(small.compareTo(BigDecimal.ZERO));

        Map<Integer,Integer> map1 = new HashMap<>();
        map1.put(1,1);
        map1.put(2,2);
//        System.out.println(map1.size());

//        for (int i = 0; i < 10000 ; i++) {
//            String s = HttpUtil.get("http://127.0.0.1:8086/hello/eat");
//            System.out.println(s);
//        }

//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }


        String s = HttpUtil.get("http://t.weather.itboy.net/api/weather/city/101010100");


        JSONObject jsonObject = JSON.parseObject(s);
        


        String regex="\\p{Punct}+";
        String digit[]=s.split(regex);
        System.out.println('\n'+"城市:"+digit[22]+digit[18]);
        System.out.println('\n'+"时间:"+digit[49]+"年"+digit[50]+"月"+digit[51]+"日"+digit[53]);
        System.out.println('\n'+"温度:"+digit[47]+"~"+digit[45]);
        System.out.println('\n'+"天气:"+digit[67]+" "+digit[63]+digit[65]);
        System.out.println('\n'+digit[69]);
//        System.out.println(s);


        Map<String,String> map2 = new HashMap<>();
        Optional<String> s1 = Optional.ofNullable(map2.get("121"));
//        System.out.println(s1.isPresent());

        String datx = "2021-01-03";

        LocalDate localDate = LocalDateTimeUtil.parseDate(datx);
        LocalDateTime localDateTime1 = localDate.atStartOfDay();
        String s2 = LocalDateTimeUtil.formatNormal(localDateTime1);
        System.out.println(LocalDateTimeUtil.now());

        int x = 7+6+2+8+6 ;

        System.out.println(x);


        List<String> strings = Arrays.asList("a", "bb", "cc", "aa", "bb", "dd", "cc");
        Map<String, Long> collect = strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<String> result = collect.entrySet().stream()
                .filter(e -> e.getValue() > 1).map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(result.toString());


        ForkJoinPool forkJoinPool1 = new ForkJoinPool(20);
        ForkJoinTask<Boolean> fs = forkJoinPool1.submit(
                () -> strings.stream().allMatch(element -> {
//            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName());
            System.out.println("线程数量：" + Thread.activeCount());
            System.out.println(element);
            return true;
        }));
//        Boolean aBoolean = fs.get();

        String xc = "-102";
        xc = xc.replace("-","");
        System.out.println(xc.length());

    }
}