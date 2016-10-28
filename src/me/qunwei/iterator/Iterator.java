package me.qunwei.iterator;

/**
 * Created by Administrator on 2016/10/20.
 */
public interface Iterator<T> {
    T next();
    boolean hasNext();
    void remove();
}
