package lambda.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/2/9
 */
public class FindStream {

    private Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5,6,7});

    @Test
    public void show(){
        Optional<Integer> optional = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println(optional.get());
    }
}
