package me.qunwei.iterator;

/**
 * Created by QunWei on 2016/10/27.
 */
public interface ListIterator<E> extends Iterator<E>{

    E next();
    boolean hasNext();

    boolean hasPrevious();
    E previous();

    int previousIndex();
    int nextIndex();

    void add(E e);
    void set(E e);
}
