package lambda.lambdaandfinal;

import org.junit.Test;

/**
 * @Auther ljn
 * @Date 2020/1/8
 * lambda中访问外部变量时,外部变量必须是final类型的
 */
public class LambdaAndFinal {


    /**
     * Variable used in lambda expression should be final or effectively final
     *
     */
    @Test
    public void test1(){
       int i = 100;
       Runnable runnable = () -> {
           System.out.println(i);
       };
    }


}
