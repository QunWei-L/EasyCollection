package me.qunwei.collection.abstract_class;

import me.qunwei.collection.List;
import me.qunwei.iterator.ListIterator;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by QunWei on 2016/10/28.
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {


    //should be use with same package
    protected AbstractList() {
    }

    public boolean add(E element) {
        return add(size(), element);
    }

    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("This method should be implement by subclass");
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    abstract public E get(int index);


    public int indexOf(E o) {

        ListIterator<E> it = ListIterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return it.previousIndex();
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return it.previousIndex();
        }

        return -1;
    }

    public int lastIndexOf(E o) {
        ListIterator<E> it = ListIterator(size());
        if (o == null) {
            while (it.hasPrevious())
                if (it.previous() == null)
                    return it.nextIndex();
        } else {
            while (it.hasPrevious())
                if (o.equals(it.previous()))
                    return it.nextIndex();
        }

        return -1;
    }

    public Iterator<E> iterator() {
        return new Itr();
    }


    //Obey the general contract when overriding equals()
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        ListIterator<E> e1 = ListIterator();
        ListIterator<?> e2 = ((List<?>) obj).ListIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    //Always override hashCode() when you override equals()
    @Override
    public int hashCode() {
        int hashcode = 1;
        for (Object e : this) {//TODO: obj -> E , an error
            hashcode = 31 * hashcode + (e == null ? 0 : e.hashCode());
            /*
            why 31,原因如下：
            A.31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终的出来的结果只能被素数本身和被乘数还有1来整除！。(减少冲突)
            B.31可以 由i*31== (i<<5)-1来表示,现在很多虚拟机里面都有做相关优化.（提高算法效率）
            C.选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，所谓的“冲突”就越少，查找起来效率也会提高。（减少冲突）
            D.并且31只占用5bits,相乘造成数据溢出的概率较小。
             */
        }
        return hashcode;
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;
        int lastRet = -1;
        int expectedModCount = modCount;

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();
            try {
                AbstractList.this.remove(lastRet);
                if (lastRet < cursor) {
                    cursor--;
                }
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public E next() {
            if (lastRet < 0)
                throw new IllegalArgumentException();
            checkForComodification();

            try {
                int i = cursor;
                E next = get(i);
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    private class ListItr extends Itr implements ListIterator<E> {
        public ListItr(int index) {
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public E previous() {
            checkForComodification();
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }


        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    protected transient int modCount;

}
