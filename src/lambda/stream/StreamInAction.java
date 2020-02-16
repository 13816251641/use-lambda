package lambda.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther ljn
 * @Date 2020/2/15
 * Stream的综合训练
 */
public class StreamInAction {

    private List<Transaction> transactions;

    @Before
    public void init(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new  Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. Find all transactions in the year 2011 and sort them by value (small to high).
    @Test
    public void test01(){
        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //2.What are all the unique cities where the traders work?
    @Test
    public void test02(){
        transactions.stream().map(t->t.getTrader().getCity())
                             .distinct()
                             .forEach(System.out::println);
    }

    //3.Find all traders from Cambridge and sort them by name.
    @Test
    public void test03(){
        transactions.stream().map(Transaction::getTrader)
                             .distinct()
                             .filter(t->t.getCity().equals("Cambridge"))
                             .sorted(Comparator.comparing(Trader::getName))
                             .forEach(System.out::println);
    }

     //4.Return a string of all traders’names sorted alphabetically
     @Test
     public void test04(){
         String s = transactions.stream().map(t -> t.getTrader().getName())
                 .distinct()
                 .sorted()
                 .reduce("", (a, b) -> a + b);
         System.out.println(s);

     }

    //5. Are any traders based in Milan?
    @Test
    public void test05(){
        boolean result = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(result);
    }

    //6.Print all transactions’values from the traders living in Cambridge.
    @Test
    public void test06(){
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge"))
                             .forEach(t->System.out.println(t.getValue()));

    }

    //7.What’s the highest value of all the transactions?
    @Test
    public void test07(){
        Optional<Integer> result = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(result.get());
    }

}

/**
 * Created by wangwenjun on 2016/10/22.
 */
class Trader{

    private final String name;
    private final String city;

    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }

}

/**
 * Created by wangwenjun on 2016/10/22.
 */
class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}











