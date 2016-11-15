一点小感悟:

List 的两种实现: 数组  和  链表. 通过Iterator访问自身方法, 达成自操作.
        ArrayList: 容量动态伸长的数组
        LinkedList: 头尾双指针节点, 进行数组操作,链表之间的删除添加

集(Set), 是不重复的集合, 通过依赖映射表键值(Map)实现, 键的唯一性.value统一用空object.
            //HashSet和TreeMap 对 map 有依赖关系

映射表(Map), 两种通用实现: HashMap 和 TreeMap

        HashMap:实际上是一个“链表散列”的数据结构，即数组和链表的结合体。
        Entry 是一个 static class，其中包含了 key 和 value，也就是键值对
        JDK1.8中，HashMap采用数组+链表+红黑树实现，当链表长度超过阈值（8）时，
        将链表转换为红黑树，这样大大减少了查找时间
        
#Fail-Fast 机制原理

我们知道 java.util.HashMap 不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了 map，
那么将抛出 ConcurrentModificationException，这就是所谓 fail-fast 策略。
fail-fast 机制是 java 集合(Collection)中的一种错误机制。 
当多个线程对同一个集合的内容进行操作时，就可能会产生 fail-fast 事件。
例如：当某一个线程 A 通过 iterator去遍历某集合的过程中，若该集合的内容被其他线程所改变了；
那么线程 A 访问集合时，就会抛出 ConcurrentModificationException 异常，产生 fail-fast 事件。   
     
     modCount 声明为 volatile，保证线程之间修改的可见性。
     
     
在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器本身的 remove 方法，
其他任何时间任何方式的修改，迭代器都将抛出 ConcurrentModificationException。因此，
面对并发的修改，迭代器很快就会完全失败，而不冒在将来不确定的时间发生任意不确定行为的风险。
注意，迭代器的快速失败行为不能得到保证，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。
快速失败迭代器尽最大努力抛出 ConcurrentModificationException。因此，编写依赖于此异常的程序的做法是错误的，
正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。

解决方案
在上文中也提到，fail-fast 机制，是一种错误检测机制。它只能被用来检测错误，因为 JDK 并不保证 fail-fast 机制一定会发生。若在多线程环境下使用 fail-fast 机制的集合，建议使用“java.util.concurrent 包下的类”去取代“java.util 包下的类”。
     

     
ConcurrentHashMap 的并发性能     
加锁操作是针对的 hash 值对应的某个 Segment，而不是整个 ConcurrentHashMap。
因为 put 操作只是在这个 Segment 中完成，所以并不需要对整个 ConcurrentHashMap 加锁。    
 
 
ConcurrentHashMap 的高并发性主要来自于三个方面：

用分离锁实现多个线程间的更深层次的共享访问。
用 HashEntery 对象的不变性来降低执行读操作的线程在遍历链表期间对加锁的需求。
通过对同一个 Volatile 变量的写 / 读访问，协调不同线程间读 / 写操作的内存可见性。 