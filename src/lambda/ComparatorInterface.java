package lambda;

@FunctionalInterface
public interface ComparatorInterface<T> {

    int compare(T o1, T o2);

}
