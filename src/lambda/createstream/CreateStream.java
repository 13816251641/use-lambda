package lambda.createstream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther ljn
 * @Date 2020/1/27
 * 创建stream的几种方式
 */
public class CreateStream {
    @Test
    public void test01(){
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.stream();
    }

    @Test
    public void test02(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
    }

    public void test03(){
        String s[] = {"a","b","c","d"};
        Stream<String> stream = Arrays.stream(s);
    }

}
