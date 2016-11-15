package me.qunwei.collection.set;

import me.qunwei.collection.Set;
import me.qunwei.collection.abstract_class.AbstractSet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by QunWei on 2016/11/12.
 */
public class HashSet<E> extends AbstractSet<E> implements Set<E>,Cloneable,Serializable{
    private transient HashMap<E,Object> map;
    private static final Object PRESENT = new Object();

    public HashSet() {
        map = new HashMap<>();
    }
    public HashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E element) {
        return map.put(element, PRESENT)==null;
    }

    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }
}
