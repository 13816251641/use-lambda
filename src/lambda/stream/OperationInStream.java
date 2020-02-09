package lambda.stream;

import org.junit.Before;
import org.junit.Test;
import wangwenjun.sample.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/2/8
 * Stream的各种操作:filter,distinct,skip,limit,map
 */

public class OperationInStream {

    private List<Integer> list;
    private List<Dish> menu;

    @Before
    public void init() {
        list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    /**
     * 过滤
     */
    @Test
    public void aboutFilter() {
        List<Integer> result = list.stream().filter(p -> p > 3).collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 祛重
     */
    @Test
    public void aboutDistinct() {
        List<Integer> result = list.stream().distinct().collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 跳过5个 6,6,7,7,1
     */
    @Test
    public void aboutSkip() {
        List<Integer> result = list.stream().skip(5).collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 限流
     * 1,2,3,4,5
     */
    @Test
    public void aboutLimit() {
        List<Integer> result = list.stream().limit(5).collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 提取
     */
    @Test
    public void aboutMap() {
/*        List<Integer> result = list.stream().map(p -> p * 2).collect(Collectors.toList());
        System.out.println(result);*/

        List<String> result = menu.stream().map(d -> d.getName()).collect(Collectors.toList());
        System.out.println(result);
    }


    /**
     * flatMap 返回的也要是一个Stream,这一点很重要!!!
     */
    @Test
    public void aboutFlatMap() {
/*        String[] words = {"Hello","World"};
        //{H,e,l,l,o},{W,o,r,l,d}
        Stream<String[]> stream = Arrays.stream(words).map(p -> p.split(""));

        //H,e,l,l,o,W,o,r,l,d
        Stream<String> stringStream = stream.flatMap(p -> Arrays.stream(p));

        stringStream.distinct().forEach(System.out::println);*/


        String words[] = {"hello", "world"};

        Stream<String[]> stream =Arrays.stream(words).map(w -> w.split(""));

        stream.flatMap(s->Arrays.stream(s)).distinct().forEach(System.out::println);

    }

}
