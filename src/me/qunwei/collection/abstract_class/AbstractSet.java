package me.qunwei.collection.abstract_class;

import me.qunwei.collection.Collection;
import me.qunwei.collection.Set;
import me.qunwei.collection.abstract_class.AbstractCollection;

import java.util.Iterator;

/**
 * Created by QunWei on 2016/11/12.
 */
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>{
    protected AbstractSet() {
    }

    @Override
    public int hashCode() {
        int h = 0;
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
        return h;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Set))
            return false;
        Collection<?> c = (Collection<?>) o;
        if (c.size() != size())
            return false;
        try {
//            TODO:return containsAll(c);
            return true;
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }
}
