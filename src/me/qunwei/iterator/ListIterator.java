package me.qunwei.iterator;

/**
 * Created by QunWei on 2016/10/27.
 */
public interface ListIterator<E> extends Iterator<E>{

    E next();
    boolean hasNext();

    boolean hasPrevious();
    E Previous();

    int previousIndex();
    int nextIndex();

    boolean add(E e);
    boolean set(E e);
}
