package me.qunwei.collection;

import me.qunwei.iterator.ListIterator;

/**
 * List接口主要是增加了面向位置的操作，允许在指定位置上操作元素，同时增加了一个能够双向遍历线性表的新列表迭代器ListIterator
 *
 * Created by QunWei on 2016/10/27.
 */
public interface List<E> extends Collection<E>{
    boolean add(int index,E element);

    /**
     *
     * @param index
     * @param element
     * @return the element previously on this position
     */
    E set(int index,E element);

    E remove(int index);

    /**
     * @param element
     * @return the index of first occurrence
     */
    int indexOf(E element);

    /**
     * @param element
     * @return the index of last occurrence
     */
    int lastIndexOf(E element);

    ListIterator ListIterator();

    ListIterator ListIterator(int index);

    List<E> sublist(int fromIndex,int toIndex);

}
