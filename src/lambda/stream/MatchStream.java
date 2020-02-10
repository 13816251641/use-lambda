package lambda.stream;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/2/9
 * match api只会返回true或者false
 */
public class MatchStream {
    private Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

    /**
     * 必须都大于3
     */
    @Test
    public void test01(){
        boolean b = stream.allMatch(n -> n > 3);
        System.out.println(b);
    }

    /**
     * 任意一个大于3即可
     */
    @Test
    public void test02(){
        boolean b = stream.anyMatch(n -> n > 3);
        System.out.println(b);
    }

    /**
     * 没有一个小于0
     */
    @Test
    public void test03(){
        boolean b = stream.noneMatch(n->n<0);
        System.out.println(b);
    }
}
