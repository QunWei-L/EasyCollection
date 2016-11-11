package me.qunwei.collection.abstract_class;

import me.qunwei.collection.Collection;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by QunWei on 2016/10/27.
 */
public abstract class AbstractCollection<E> implements Collection<E> {


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     *
     * @param element
     * @return operate flag
     */
    @Override
    public boolean remove(Object element) {
        Iterator it = iterator();
        if (null == element) {
            while (it.hasNext()) {
                if (it.next() == null){
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()){
                if (element.equals(it.next())){
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object elements) {
        Iterator it = iterator();

        if (null == elements){
            while (it.hasNext()){
                if (it.next() == null){
                    return true;
                }
            }
        }else {
            while (it.hasNext()){
                if (elements.equals(it.next())){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Object[] toArray() {

        Object[] res = new Object[size()];
        Iterator it = iterator();
        for (int i = 0; i < res.length; i++) {
            if (!it.hasNext())
                return Arrays.copyOf(res, i);
            res[i] = it.next();
        }
        //more elements than expected
        //return it.hasNext()?finishToArray(res,it):res;//TODO:重新分配数组元素,扩容
        return res;
    }
}
