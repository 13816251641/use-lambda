package lambda.stream;

import org.junit.Before;
import org.junit.Test;
import wangwenjun.sample.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by wangwenjun on 2016/10/18.
 */
public class SimpleStream {
    private List<Dish> menu;

    @Before
    public void init(){
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


    @Test
    public void test(){
        List<String> result = menu.stream().filter(d -> d.getCalories() > 300).map(d -> d.getName()).collect(Collectors.toList());

    }


}
