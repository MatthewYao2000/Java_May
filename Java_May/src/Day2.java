import java.lang.reflect.Field;
import java.util.*;

public class Day2 {
    // Agenda: Array List, stack + vector, priority queue, hashmap, treeMap, set
    //
    /**
     * ArrayList
     *      extends: Random Access: support fast random access and retrieve any random elements at the
     *      the same speed. Ex: we have a collection: 1 million objects/data, I want to access the first one
     *      element and the 99999th element, the randomAccess offers the same speed.
     *
     *         hint: when you create an empty list, the capacity is 0. if you add the first element, the
     *          capacity is changed from 0 to 10(10 is default value of capacity)
     * add() in arrayList: when you add the first element!!
     *      step 1:  public boolean add(E e) {
     *         ensureCapacityInternal(size + 1);  // Increments modCount!!
     *         elementData[size++] = e;
     *         return true;
     *     }
     *      step 2: private void ensureCapacityInternal(int minCapacity) {
     *         ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
     *     }
     *     step 3:   private static int calculateCapacity(Object[] elementData, int minCapacity) {
     *         if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) { // add the first element
     *          // you can define your own minCapacity,
     *             return Math.max(DEFAULT_CAPACITY, minCapacity); // max(de, min)
     *         }
     *         return minCapacity;
     *     }
     *     step 4:
     *         private void ensureExplicitCapacity(int minCapacity) {
     *         modCount++;
     *
     *         // overflow-conscious code
     *         if (minCapacity - elementData.length > 0) //
     *             grow(minCapacity);
     *     }
     *     what is modCount?
     *     timeline ---------------------
     *     thread 1 --> add(1) 3 -------------------------------- 3 != 4----update the size of list---------
     *     thread 2 -------------add(2) 3--update the size of list 4--------
     *     in this case, the java will throw ConcurrentModificationExceptiopn
     *
     *     step 5:  important!!!!
     *       private void grow(int minCapacity) {
     *         // overflow-conscious code
     *         //
     *         int oldCapacity = elementData.length;
     *         *****important***************
     *         // old >> 1 == old / 2
     *         // old << 1 == old * 2
     *         int newCapacity = oldCapacity + (oldCapacity >> 1);// new = old * 1.5
     *         *********************************
     *         if (newCapacity - minCapacity < 0)
     *             newCapacity = minCapacity;
     *         if (newCapacity - MAX_ARRAY_SIZE > 0)
     *             newCapacity = hugeCapacity(minCapacity);
     *         // minCapacity is usually close to size, so this is a win:
     *         elementData = Arrays.copyOf(elementData, newCapacity);
     *     }
     *     when do the java call the grow() function: we have  List<Integer> list = new ArrayList<>();
     *     first time to call grow function: we add the first element
     *     second time to call grow function: we add the 11th element -> default is 10
     *
     *     you need to know the time complexity of add(), get(), remove(), indexOf()
     */
    /***************  stack + vector************************************
     *  Important: stack and vector use synchronized keyword to achieve thread safe!!!!!
     *   FILO data structure
     *
     *   resize():
     *       private void grow(int minCapacity) {
     *         // overflow-conscious code
     *         int oldCapacity = elementData.length;
     *         //**************** important****************
     *         capacityIncrement is your defined, if you want
     *         if you donot define capacityIncrement: new capacity = old * 2
     *         if you define capacityIncrement: new capacity = old + capacityIncrement;
     *         int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
     *                                          capacityIncrement : oldCapacity);
     *                    *******************************************
     *         if (newCapacity - minCapacity < 0)
     *             newCapacity = minCapacity;
     *         if (newCapacity - MAX_ARRAY_SIZE > 0)
     *             newCapacity = hugeCapacity(minCapacity);
     *         elementData = Arrays.copyOf(elementData, newCapacity);
     *     }
     *   you need to know the time complexity of push, pop, size()
     *
     *    leetcode: implement stack by using queue
     *
     *    stack(vector) vs arraylist
     *    when we add an element into arraylist, we just use indexing to add it.
     *    if your system has a lot of adding or accessing, please consider arraylist
     *
     *    vector uses a lot of synchronized keywords, good thing: thread safe, bad thing: slower than arraylist
     *    grow function is diff
     *     mark the normal list to synchronized
     *      List<String> synchronizedList = Collections.synchronizedList(list);
     *
     *
     * ***************** linkedlist + deque + heap*****************
     *  linked list: uses double linked list data structure
     *   it is not thread safe, does not support random access, bc it does not implement randomAccess
     *
     *    void linkLast(E e) {
     *         final Node<E> l = last; // the current linked list of the last node
     *         final Node<E> newNode = new Node<>(l, e, null);// create a new node, prev node is l, curr node is e
     *         last = newNode; // update new last node, which is e
     *         if (l == null)// your linked list is empty,
     *             first = newNode;
     *         else // if linked list is not empty,
     *             l.next = newNode;
     *         size++;
     *         modCount++;
     *     }
     *
     *     you need to know the time complexity of add(), add(index,element), remove(index)
     *
     *     deque:
     *     deque is double ended queue, which means we can use deque as FILO and FIFO
     *
     *
     *     queue   vs   deque methods
     *     add()        addLast()  : if failed to add, throws exception
     *     offer()      offerLast() if failed, return false
     *     remove()     removeFirst(): if failed to remove, throws exception
     *     poll()       pollFirst(): if failed, return null
     *     element()    getFirst() if failed to getFirst, throws exception
     *     peek()   peekFirst() : if failed, return null
     *
     *     stack vs deque methods
     *
     *     push     addFirst()
     *     pop()    removeFirst()
     *     peek()   getFirst()
     *     nothing offerFirst()
     *
     *     ***************** heap*****************
     *     the default priority queue is min heap in java, it is max heap in c++
     *     the under laying data structure is array
     *     why? we want to get an element as fast as we can
     *index
     *     [3,5,10,7,9,15,11,13,20,12]
     *                      3
     *                      /\
     *                      5 10
     *                      /\    /\
     *                      7 9  15 11
     *                    /\  /
     *                  13 20 12
     *
     *                  leftNo = index * 2 + 1
     *                  rightNo = index * 2 + 2
     *                  parentNo = (child index - 1) / 2
     *      grow:
     *     private void grow(int minCapacity) {
     *         int oldCapacity = queue.length;
     *         *****important: // Double size if small; else grow by 50%
     *         int newCapacity = oldCapacity + ((oldCapacity < 64) ?
     *                                          (oldCapacity + 2) :
     *                                          (oldCapacity >> 1));
     *         // overflow-conscious code
     *         if (newCapacity - MAX_ARRAY_SIZE > 0)
     *             newCapacity = hugeCapacity(minCapacity);
     *         queue = Arrays.copyOf(queue, newCapacity);
     *     }
     *
     *      leetcode: return top kth element in a given arraylist
     *     ***********************hashmap****************************
     *     hashMap is key value data structure
     *     allows you find a value by using the corresponding key
     *     1: "Matt"
     *
     *     In java 8 or new version: hashMap: linked list -> red black tree
     *     hashmap -> array: [[1][2][3][4][5][6].. [63] - add(64)]
     *                                                          \linkedlist
     *                                                          elements1
     *                                                          \
     *                                                          elements2
     *                                                          \element7
     *                                                          \element8
     *                                                          transfer
     *                                                          elem1
     *                                                          /\
     *                                                          elem2 elem3
     *                                                          /\   /\
     *                                                          e4 e5
     *     ***important** when hashmap starts to transfer from linked list to red - black tree
     *          the len of array is greater than and equal to 64, and the linked list is greater than 8
     *      ***important** when hashmap starts to transfer from red-black tree to linked list
     *              the number of elements in the red-black three is less than 6
     *
     *
     *     hash function:
     *      static final int hash(Object key) {
     *         int h;
     *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     *     }
     *
     *     the process of hashing function
     *     1010 = 10
     *    h = hashCode(); 1111 1111 1111 1111 1111 0000 1110 1010
     *    h >>> 16:       0000 0000 0000 0000 1111 1111 1111 1111
     *  h ^ (h >>> 16):   1111 1111 1111 1111 0000 1111 0001 0101
     *  (n - 1) & hash    0000 0000 0000 0000 0000 0000 0000 1111 what is n? n is len of array
     *                    0000 0000 0000 0000 0000 0000 0000 0101 = 5
     *     you need to know w
     *          DEFAULT_LOAD_FACTOR: represents the precentage of total capacity: the default value is 0.75
     *              the table will be resized when the number of elements reach 75% of the total capacity
     *              suppose: 100, 75 -> 76th -> resize
     *          threshold: represents the max number of elements allowed to be stored, -> resize, if too much value
     *
     *     resize()
     *     final Node<K,V>[] resize() {
     *         Node<K,V>[] oldTab = table;
     *         // remember the current capacity
     *         int oldCap = (oldTab == null) ? 0 : oldTab.length;
     *         // threshold = capacity * load factor
     *         int oldThr = threshold;
     *         int newCap, newThr = 0;
     *         if (oldCap > 0) {
     *             if (oldCap >= MAXIMUM_CAPACITY) {
     *                 threshold = Integer.MAX_VALUE;
     *                 return oldTab;
     *             }// important: new threshold = oldThreshold * 2
     *             else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
     *                      oldCap >= DEFAULT_INITIAL_CAPACITY)
     *                 newThr = oldThr << 1; // double threshold
     *         }
     *         else if (oldThr > 0) // initial capacity was placed in threshold
     *             newCap = oldThr;
     *         else {               // zero initial threshold signifies using defaults
     *             newCap = DEFAULT_INITIAL_CAPACITY;
     *             newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
     *         }
     *         if (newThr == 0) {
     *             float ft = (float)newCap * loadFactor;
     *             newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
     *                       (int)ft : Integer.MAX_VALUE);
     *         }
     *         threshold = newThr;
     *         @SuppressWarnings({"rawtypes","unchecked"})
     *         Node<K, V>[] newTab = (Node<K,V>[])new Node[newCap];
     *         table = newTab;
     *         if (oldTab != null) {
     *             for (int j = 0; j < oldCap; ++j) {
     *                 Node<K,V> e;
     *                 if ((e = oldTab[j]) != null) {
     *                     oldTab[j] = null;
     *                     if (e.next == null)
     *                         newTab[e.hash & (newCap - 1)] = e;
     *                     else if (e instanceof TreeNode)//
     *                         ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
     *                     else { // preserve order
     *                         Node<K,V> loHead = null, loTail = null;
     *                         Node<K,V> hiHead = null, hiTail = null;
     *                         Node<K,V> next;
     *                         do {
     *                             next = e.next;
     *                             if ((e.hash & oldCap) == 0) {
     *                                 if (loTail == null)
     *                                     loHead = e;
     *                                 else
     *                                     loTail.next = e;
     *                                 loTail = e;
     *                             }
     *                             else {
     *                                 if (hiTail == null)
     *                                     hiHead = e;
     *                                 else
     *                                     hiTail.next = e;
     *                                 hiTail = e;
     *                             }
     *                         } while ((e = next) != null);
     *                         if (loTail != null) {
     *                             loTail.next = null;
     *                             newTab[j] = loHead;
     *                         }
     *                         if (hiTail != null) {
     *                             hiTail.next = null;
     *                             newTab[j + oldCap] = hiHead;
     *                         }
     *                     }
     *                 }
     *             }
     *         }
     *         return newTab;
     *     }
     *
     *      put function:
     *      the process of put:
     *          1:calculating hash value, we want to find the index where we need to put our value
     *          2: if there is null(no hash collision) then hashMap stores the value
     *          3: if there exits some value(s)(hash collision) hashmap will do the following steps:
     *                  3.1: if hashmap is using red-black tree, then call red-black tree insertion method to put valu
     *                  3.2: is not using red-black tree, then put the value and hashmap will
     *                  check the current size of linked list, if the hashmap meets the resize
     *                  requirements, then hashmap will transfer the structure to red-black tree
     *          4: if there exits duplicate key, then replace the value
     *          5: check the size, if the size is greater than threshold, resizing
     *
     *
     *          TreeMap is nothing but the key is ordered
     *
     *          hashset is not allow to have duplicated value
     *
     *
     */
    public static void main(String[] args) throws Exception {
        Set<String> set = new HashSet<>();
        set.add("apple");
        if(!set.contains("apple")){
            set.add("banana");
        }{
            System.out.println("apple is here");
        }
        // heap
        //hashMap

        List<String> list = new ArrayList<>();


        List<String> synchronzedList = Collections.synchronizedList(list);

        Deque<String> deque =  new ArrayDeque<>();
        deque.addFirst("apple");
        deque.addFirst("banana");

        deque.addLast("orange");
        deque.addLast("peach");


        String firstElement = deque.removeFirst();
        String lastElement = deque.removeLast();
        // first way to iterate over a deque
        for(String str : deque){
            System.out.println(str);
        }
        //second way
        Iterator<String> iterator = deque.iterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            System.out.println(element);
        }
        // peek: check the element and not delete the elemet
        String peekFirstEle = deque.peekFirst();
        String peekLastEle = deque.peekLast();

        // offer() vs add(): they are same
        // poll vs peak
        // poll return and delete, peek: return and not delete

        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(3,3);
        treeMap.put(1,1);
        treeMap.put(2,2);
        int min = treeMap.pollFirstEntry().getKey();
        System.out.println(min);
        TreeMap<Integer,Integer> treeMap1 = new TreeMap<>(Collections.reverseOrder());
        treeMap1.put(3,3);
        treeMap1.put(1,1);
        treeMap1.put(2,2);
        int max = treeMap1.pollFirstEntry().getKey();
        System.out.println(max);


    }
    static int getCapacity(List l) throws Exception{
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        return ((Object[])field.get(l)).length;
    }
}
