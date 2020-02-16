package lambda.methodreference;

import org.junit.Test;
import wangwenjun.sample.Apple;
import wangwenjun.sample.ComplexApple;
import wangwenjun.sample.ThreeFunction;

import java.util.Arrays;
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

        /* Comparator<? super Apple> 因为入参都是Apple或者Apple的子类,所以lambda用Apple来接收没毛病 */
        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));

        System.out.println(list);

        list.stream().forEach(a -> System.out.println(a));//普通遍历

        System.out.println("==========================");

        list.stream().forEach(System.out::println);//方法推导

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
        IntSupplier intSupplier = s12::length;
        System.out.println(intSupplier.getAsInt());

        Supplier<String> supplier = String::new;
        String s = supplier.get();
        System.out.println(s);

    }

    /**
     * 测试各种apple
     * @param
     */
    public void testApple(){
        /* new Apple对象需要String和Long构成构造方法,返回一个Apple */
        BiFunction<String, Long, Apple> appleFunction = Apple::new;
        Apple apple = appleFunction.apply("red", 100L);
        System.out.println(apple);

        /* new ComplexApple对象需要String,Long,Long,并返回ComplexApple*/
        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("Green", 123L, "FuShi");
        System.out.println(complexApple);
    }

    /**
     * 自己的实践
     */
    @Test
    public void test(){

        String s = new String("hello");
        Supplier<Integer> supplier = s::length;
        System.out.println(supplier.get());

        Function<String, Integer> stringIntegerFunction = String::length;
        System.out.println(stringIntegerFunction.apply("a"));

        BiFunction<String, Integer, Character> stringIntegerCharacterBiFunction = String::charAt;
        System.out.println(stringIntegerCharacterBiFunction.apply("av",1));

    }

}
