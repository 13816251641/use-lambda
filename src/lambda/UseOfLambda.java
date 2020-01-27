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
 * java��lambda���ʽ��ʹ��,�μ�https://www.cnblogs.com/franson-2016/p/5593080.html��
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
              ������ô���:? super Father -> ���һ��Ҫ��Father����Father������,
              �����ǵ�lambda�ĺ����Ǹ�����ص�ʹ�õ�,����p1,p2ΪFather���;�һ��
              û��,��Ϊ������Խ�������Ķ���
            */
           ComparatorInterface<? super Father> c = (p1, p2)->0;
           c.compare(new Sonn(),new Sonn());
           c.compare(new Father(),new Father());
    }

    /**
       ����С����
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
        /* ������ֻҪ��Father����Father�ĸ��඼���� */
        Comparator<? super Father> comparator2 = comparator;
        /* ? super Father o1:ֻ�ܽ���Father����Father�����൱����� */
        comparator2.compare(new Sonn(),new Sonn());
    }

    @Test
    public void useComparatorWithLambda(){
        /*
           ���s1,s2����Ҫ������,��Ϊ��߷����Ѿ���æָ����,���������lambda�Լ��Ĺ���
         */
        Comparator<String> sortByName = (s1, s2) -> (s1.compareTo(s2));
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        /*
          players��String���� -> Comparator<? super String>,lambda�ͻὫ����
          ������Ƶ�ΪString����,��Ϊ����ֻ�ܽ���String�����String������
         */
        Arrays.sort(players, (s1,s2) -> {return s1.compareTo(s2);});
    }

    @Test
    public void useOfStreamWithLambda(){
        System.out.println("���г���Ա������:");
        /* Consumer<? super UseOfLambda.Person>����Consumer��accept����ֻ�ܽ���
         * UseOfLambda.Person�����������,����lamdba��UseOfLambda.Person��������
         * ��β����д�
         */
        javaProgrammers.forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("������Ա��н 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        System.out.println("��������н���� $1,400 ��PHP����Ա:");
        phpProgrammers.stream().filter(person -> person.getSalary() > 1400).forEach(p -> System.out.printf("%s %s;",p.getFirstName(), p.getLastName()));


        System.out.println("��ǰ���3�� Java programmers:");
        javaProgrammers.stream().limit(3).forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("���� salary ���� Java programmers:");
        List<Person> sortedJavaProgrammers  = javaProgrammers.stream().sorted((p1, p2) -> p1.getSalary() - p2.getSalary()).collect(Collectors.toList());
        sortedJavaProgrammers.forEach(p -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));

        System.out.println("������͵� Java programmer:");
        Person pers = javaProgrammers.stream().min((p1, p2) -> (p1.getSalary() - p2.getSalary())).get();
        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

        System.out.println("�� JAVA programmers �� first name ƴ�ӳ��ַ���:");
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



