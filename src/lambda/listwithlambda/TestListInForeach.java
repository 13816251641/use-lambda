package lambda.listwithlambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther ljn
 * @Date 2020/1/4
 */
public class TestListInForeach {

    /**
     * return并不会阻止list的迭代
     */
    @Test
    public void test01(){
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(e->{
            System.out.println(e);
            return;
        });


    }

}
