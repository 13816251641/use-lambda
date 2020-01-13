package lambda.methodreference;

import org.junit.Test;
import wangwenjun.sample.Apple;
import wangwenjun.sample.ComplexApple;
import wangwenjun.sample.ThreeFunction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Created by wangwenjun on 2016/10/16.
 * 测试使用方法推导
 */
public class MethodReference {

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    @Test
    public void testUseConsumer(){
        useConsumer(s -> System.out.println(s), "Hello Alex");
        useConsumer(System.out::println, "Hello Wangwenjun");
    }




    @Test
    public void testList(){
        List<Apple> list = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

        System.out.println(list);

        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));

        System.out.println(list);

        list.stream().forEach(a -> System.out.println(a));//遍历

        System.out.println("==========================");

        list.stream().forEach(System.out::println);

    }

    /**
     * 测试方法推导
     */
    @Test
    public void testMethodReference(){
        Function<String, Integer> f = Integer::parseInt;//parseInt是静态方法
        Integer result = f.apply("123");
        System.out.println(result);


        BiFunction<String, Integer, Character> f2 = String::charAt;
        Character c = f2.apply("hello", 2);
        System.out.println(c);

        String string = new String("Hello");
        Function<Integer, Character> f3 = string::charAt;
        Character c2 = f3.apply(4);
        System.out.println(c2);

        String s12 = new String("hello123");
        IntSupplier runnable = s12::length;
        System.out.println(runnable.getAsInt());

        Supplier<String> supplier = String::new;
        String s = supplier.get();
        System.out.println(s);

    }

    /**
     * 测试各种apple
     * @param
     */
    public void testApple(){
        BiFunction<String, Long, Apple> appleFuntion = Apple::new;
        Apple apple = appleFuntion.apply("red", 100L);
        System.out.println(apple);

        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("Green", 123L, "FuShi");
        System.out.println(complexApple);
    }

}
