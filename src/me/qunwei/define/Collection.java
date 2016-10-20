package me.qunwei.define;


/**
 * Created by Administrator on 2016/10/20.
 */
public interface Collection<T> {
    boolean add(T element);
    Iterator<T> iterator();
}
