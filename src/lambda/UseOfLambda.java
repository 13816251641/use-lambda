package lambda;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Auther ljn
 * @Date 2019/12/26
 * java中lambda表达式的使用,参见https://www.cnblogs.com/franson-2016/p/5593080.html的
 */
public class UseOfLambda {

    private List<Person> javaProgrammers;
    private List<Person> phpProgrammers;

    @Before
    public void init(){
        javaProgrammers = new ArrayList<>();
        javaProgrammers.add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
        javaProgrammers.add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
        javaProgrammers.add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
        javaProgrammers.add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
        javaProgrammers.add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
        javaProgrammers.add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
        javaProgrammers.add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
        javaProgrammers.add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
        javaProgrammers.add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
        javaProgrammers.add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));

        phpProgrammers = new ArrayList<>();
        phpProgrammers.add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
        phpProgrammers.add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
        phpProgrammers.add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
        phpProgrammers.add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
        phpProgrammers.add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
        phpProgrammers.add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
        phpProgrammers.add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
        phpProgrammers.add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
        phpProgrammers.add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
        phpProgrammers.add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));


    }

    @Test
    public void useComparatorInterface(){
           /*
              我是这么想的:? super Father -> 入参一定要是Father或者Father的子类,
              而我们的lambda的函数是给程序回调使用的,所以p1,p2为Father类型就一定
              没错,因为父类可以接受子类的对象。
            */
           ComparatorInterface<? super Father> c = (p1, p2)->0;
           c.compare(new Sonn(),new Sonn());
           c.compare(new Father(),new Father());
    }

    /**
       泛型小尝试
     */
    @Test
    public void skipOfGeneric(){
        Comparator<GrandFather> comparator = new Comparator<GrandFather>() {
            @Override
            public int compare(GrandFather o1, GrandFather o2) {
                System.out.println("skipOfGeneric");
                return 0;
            }
        };
        /* 泛型类只要是Father或者Father的父类都可以 */
        Comparator<? super Father> comparator2 = comparator;
        /* ? super Father o1:只能接受Father或者Father的子类当做入参 */
        comparator2.compare(new Sonn(),new Sonn());
    }

    @Test
    public void useComparatorWithLambda(){
        /*
           入参s1,s2不需要加类型,因为左边泛型已经帮忙指定了,这个功能是lambda自己的功能
         */
        Comparator<String> sortByName = (s1, s2) -> (s1.compareTo(s2));
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        /*
          players是String数组 -> Comparator<? super String>,lambda就会将这里
          的入参推导为String类型,因为方法只能接受String类或者String的子类
         */
        Arrays.sort(players, (s1,s2) -> {return s1.compareTo(s2);});
    }

    @Test
    public void useOfStreamWithLambda(){
        System.out.println("所有程序员的姓名:");
        /* Consumer<? super UseOfLambda.Person>代表Consumer的accept方法只能接受
         * UseOfLambda.Person类或者其子类,所以lamdba用UseOfLambda.Person类来代表
         * 入参不会有错
         */
        javaProgrammers.forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream().filter(person -> person.getSalary() > 1400).forEach(p -> System.out.printf("%s %s;",p.getFirstName(), p.getLastName()));


        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream().limit(3).forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("根据 salary 排序 Java programmers:");
        List<Person> sortedJavaProgrammers  = javaProgrammers.stream().sorted((p1, p2) -> p1.getSalary() - p2.getSalary()).collect(Collectors.toList());
        sortedJavaProgrammers.forEach(p -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));

        System.out.println("工资最低的 Java programmer:");
        Person pers = javaProgrammers.stream().min((p1, p2) -> (p1.getSalary() - p2.getSalary())).get();
        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

        System.out.println("将 JAVA programmers 的 first name 拼接成字符串:");
        String javaDevelopers = javaProgrammers.stream().map(Person::getFirstName).collect(Collectors.joining(";"));
        System.out.println(javaDevelopers);
    }

    @Data
    private class Person {
        private String firstName;
        private String lastName;
        private String job;
        private String gender;
        private int salary;
        private int age;

        public Person(String firstName, String lastName, String job,
                      String gender, int age, int salary)       {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.job = job;
            this.salary = salary;
        }
    }

    class GrandFather{

    }

    class Father extends GrandFather{

    }

    class Sonn extends Father{

    }

}



