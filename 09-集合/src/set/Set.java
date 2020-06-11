package set;

public interface Set<E> {

    int Size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }
}
