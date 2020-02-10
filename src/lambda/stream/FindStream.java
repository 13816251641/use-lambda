package lambda.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/2/9
 * find是会有符合项返回的,但它返回的只是一个Optional,
 * 我们要会Optional的api
 */
public class FindStream {

    private Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5,6,7});

    @Test
    public void test01(){
        Optional<Integer> optional = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println(optional.get());

    }
}
