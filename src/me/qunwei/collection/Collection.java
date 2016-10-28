package me.qunwei.collection;


import me.qunwei.iterator.Iterator;

/**
 * Created by Administrator on 2016/10/20.
 */
public interface Collection<T> {

    // basic operations

    boolean add(T element);

    boolean remove(T element);


    // query operations

    Iterator<T> iterator();

    boolean isEmpty();

    int size();

    boolean contains(Object elements);

    Object[] toArray();

    //reduce the complex
//    <T> T[] toArray(T[] target);


    //TODO: add the operations for all collection,such as containsAll(Conllection<T> collection)


    //TODO: add the stream() and parallelStream() in the java 1.8



}
