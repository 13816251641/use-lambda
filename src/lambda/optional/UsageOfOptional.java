package lambda.optional;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

/**
 * @Auther ljn
 * @Date 2020/2/17
 * Optional的使用
 */
public class UsageOfOptional {
    /**
     * 获取车名,利用map简化了我们if/else判断的流程
     */
    @Test
    public void test01(){
        Person person = new Person();
        Optional<String> carName = Optional.ofNullable(person).map(Person::getCar).map(Car::getName);
        carName.ifPresent(t->{
            System.out.println("has value");
        });
    }


}

@Data
class Person{
     private String name;
     private Integer age;
     private Car car = new Car();//人有车
}

@Data
class Car{
    private String name = "BMW";
}
