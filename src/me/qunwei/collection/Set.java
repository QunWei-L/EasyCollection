package me.qunwei.collection;

/**
 * Created by QunWei on 2016/11/12.
 */
public interface Set<E> extends Collection<E>{
    boolean equals(Object o);
    int hashCode();
}
