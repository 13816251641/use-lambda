package lambda.collector;

import wangwenjun.sample.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther ljn
 * @Date 2020/2/19
 * 介绍Collector接口
 */
public class IntroduceCollector {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));
        Map<String, List<Apple>> map1 = groupByFunction(list);
        Map<String, List<Apple>> map2 = groupByCollector(list);
        System.out.println(map2);
    }

    /*
       利用lambda进行分组,颜色相同的是一组
     */
    private static Map<String,List<Apple>> groupByFunction(List<Apple> apples){
        Map<String,List<Apple>> map = new HashMap<>();
        apples.stream().forEach(e->{
            List<Apple> colorList = Optional.ofNullable(map.get(e.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(e.getColor(), list);
                return list;
            });
            colorList.add(e);
        });
        return map;
    }

    /*
       牛逼 直接一行代码解决
     */
    private static Map<String,List<Apple>> groupByCollector(List<Apple> apples){
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
