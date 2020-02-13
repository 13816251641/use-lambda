package lambda.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/2/12
 */
public class NumericStream {

    /**
     * Stream<Integer> -> IntStream
     * Integer类型对内存的消耗是大于int的
     */
    @Test
    public void test01(){
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        /*
            IntStream intStream = stream.mapToInt(i -> i.intValue());
        */

        int sum = stream.mapToInt(i -> i.intValue()).filter(i -> i > 3).sum();
        System.out.println(sum);

    }


    /**
     * 算出9和1-100中的那些数字可以组成勾三股四
     */
    @Test
    public void test02(){
        int a = 9;
        IntStream.rangeClosed(1,100)
                .filter(n-> Math.sqrt(a*a+n*n) % 1 == 0)
                .mapToObj(n->new int[]{a,n,(int)Math.sqrt(a*a+n*n)})
                .forEach(array->{
                        System.out.println(array[0]+":"+array[1]+":"+array[2]);
                    }
                );



    }



}
