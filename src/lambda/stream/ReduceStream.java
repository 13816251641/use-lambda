package lambda.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/2/10
 * reduce相当于是聚合
 */
public class ReduceStream {

    private Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,7,4,5,6});

    /**
     * 测试累加
     */
    @Test
    public void test01(){
        Integer reduce = stream.reduce(0, (i, j) -> i + j );
        System.out.println(reduce);
    }

    /**
     * 测试累加2,没有初始值只能得到optional
     */
    @Test
    public void test02(){
        Optional<Integer> optional = stream.reduce((i, j) -> i + j);
        System.out.println(optional.get());
    }

    /**
     * 得到最大值
     */
    @Test
    public void test03(){
      /*  Optional<Integer> optional = stream.reduce((i, j) ->{
            return i>j?i:j;
        });*/
        Optional<Integer> optional = stream.reduce(Integer::max);
        System.out.println(optional.get());
    }

    /**
     * 得到最小值
     */
    @Test
    public void test04(){
      /*  Optional<Integer> optional = stream.reduce((i, j) ->{
            return i>j?j:i;
        });*/
        Optional<Integer> optional = stream.reduce(Integer::min);
        System.out.println(optional.get());
    }

    /**
     * 得到乘积
     */
    @Test
    public void test05(){
        Optional<Integer> optional = stream.reduce((i, j) ->{
            return i*j;
        });
        optional.ifPresent(System.out::print);
    }




}
