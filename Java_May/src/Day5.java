import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Day5 {

    //volatile:
        // in java, we use JMM(java memory model) to manage the communication between differet
        // threads

    /**
     *  thread A                             thread B
     *  [local memory A: local count]                     [ local memory B]
     *     |        <----        JMM  ----->     |
     *
     *  [                   main memory: volatile int count                   ]
     *
     * java compiler : .java into .class
     * int volatile c = 1;
     *
     * int  volatile b = 1;
     *
     * int  volatile d = 1;
     *
     * int volatile  a = 1;
     *
     * ....
     * int e = a + b; // this must be load after a and b
     * int f = c + d;  // this must be loaded after c  and d
     * synchronized + static: class, "this" : current object
     *
     *
     * CAS: stands for compare and swap(set), which is atomic operation in java concurrency programming language
     * what is atomic operation?
     *  it is an unit of operations always execute together or none of them executes.
     *  {
     *
     *      a = a..
     *      b = ...
     *      other operations....
     *t
     *  }
     * V: the value you would like to change
     * E: the expected value
     * N: the new value you would like to set
     *
     * thread A: increase i by one, it reads i = 2 you set v = 2, and E = 2
     *  then thread A increase i by one i = 3, thread A will compare V and E, IF there are equal to each other,
     *  then thread A allows to set N = 3
     * thread B: [i = 2 where i is V]
     *
     * ABA problem:
     *  timeline---------------------------------------------------
     *          thread A: gets the value V at the time 1--------------------------------------------------------------------------------------time 4 where time 4 is later than time 3: change the value V-----------------------------------
     *          thread B:          get the value V and change the value  at the time 2, where time 2 is later than time 1 ---------time 3: change the value V back to the original value------
     *
     *
     * concurrentHashMap
     *  [[][]...[]] -> 16 segments-> 16 threads -> synchronized segments[seg1]
     *  before java 8: array + linked list
     *  java 8 : node array + linked or red-black tree
     *  can we have null value in concurrent hashmap?
     *              no!
     *
     * put()
     *
     * final V putVal(K key, V value, boolean onlyIfAbsent) {
     *         if (key == null || value == null) throw new NullPointerException();
     *         int hash = spread(key.hashCode()); // step 1 finding the index
     *         int binCount = 0;
     *         for (Node<K,V>[] tab = table;;) {
     *             Node<K,V> f; int n, i, fh;
     *             if (tab == null || (n = tab.length) == 0)
     *                 tab = initTable(); // step 2 initial the table
     *             else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {//  if the table is initialzed and empty, then just put the value at index = (n -1) & hash where n is len of array
     *                 if (casTabAt(tab, i, null,
     *                              new Node<K,V>(hash, key, value, null)))
     *                     break;                   // no lock when adding to empty bin
     *             }
     *             else if ((fh = f.hash) == MOVED)// step 4: resize the table
     *                 tab = helpTransfer(tab, f);
     *             else {
     *                 V oldVal = null;
     *                 synchronized (f) {// if we are using linked list
     *                     if (tabAt(tab, i) == f) {// process of putting
     *                         if (fh >= 0) {
     *                             binCount = 1;
     *                             for (Node<K,V> e = f;; ++binCount) {
     *                                 K ek;
     *                                 if (e.hash == hash &&
     *                                     ((ek = e.key) == key ||
     *                                      (ek != null && key.equals(ek)))) {
     *                                     oldVal = e.val;
     *                                     if (!onlyIfAbsent)
     *                                         e.val = value;
     *                                     break;
     *                                 }
     *                                 Node<K,V> pred = e;
     *                                 if ((e = e.next) == null) {
     *                                     pred.next = new Node<K,V>(hash, key,
     *                                                               value, null);
     *                                     break;
     *                                 }
     *                             }
     *                         }
     *                         else if (f instanceof TreeBin) {// if we are using red-black three
     *                             Node<K,V> p;
     *                             binCount = 2;
     *                             if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
     *                                                            value)) != null) {
     *                                 oldVal = p.val;
     *                                 if (!onlyIfAbsent)
     *                                     p.val = value;
     *                             }
     *                         }
     *                     }
     *                 }
     *                 if (binCount != 0) {
     *                     if (binCount >= TREEIFY_THRESHOLD)// change to tree
     *                         treeifyBin(tab, i);
     *                     if (oldVal != null)
     *                         return oldVal;
     *                     break;
     *                 }
     *             }
     *         }
     *         addCount(1L, binCount);
     *         return null;
     *     }
     * get():
     *  public V get(Object key) {
     *         Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
     *         int h = spread(key.hashCode()); // find the index
     *         if ((tab = table) != null && (n = tab.length) > 0 && // the value of header = the value that we want to return
     *             (e = tabAt(tab, (n - 1) & h)) != null) {
     *             if ((eh = e.hash) == h) {
     *                 if ((ek = e.key) == key || (ek != null && key.equals(ek)))// if hash and key are equal to eh and e.key, which means we find the value, just return it.
     *                     return e.val;
     *             }
     *             else if (eh < 0)// we are using the red-black tree to store each values.
     *                 return (p = e.find(h, key)) != null ? p.val : null;
     *             while ((e = e.next) != null) {// iterate over the linked list
     *                 if (e.hash == h &&
     *                     ((ek = e.key) == key || (ek != null && key.equals(ek))))
     *                     return e.val;
     *             }
     *         }
     *         return null;
     *     }
     * blocking queue
     *
     * bq is used by producer - consumer model:
     * it provides thread safe
     *
     * producer 1: thread 1                     -> consumer thread 1
     *producer 2: thread 2    ->[blocking queue] -> consumer thread 2
     *producer 3: thread 3                       -> consumer thread 3
     *
     * put method in blocking queue
     *
     *
     *
     * the drawbacks of synchronized keyword
     * 1: decreased performance:
     * 2: difficulty in debugging:
     * 3: deadlock: thread A holds the resources that thread B wants to access, and thread B holds resouces that thread A wants to access,
     *
     *
     *  pessimistic lock vs optimistic lock
     *  with pessimistic lock: other transactions must wait until the current lock is released
     *  no one can read or modify the resources: reentrantlock
     *
     *  optimistic lock
     *  other transactions are allowed to read the resource concurrently, but if any one want to modify
     *  the resources, it will not allowed: atomicInteger
     *
     *
     *  what is reentrantlock?
     *  features:
     *      1: mutual exclusion: preventing multiple threads from accessing the same code or data concurrently
     *      2: reentrancy: A thread that has already acquired the lock can get the lock again without deadlocking itself.
     *      3:Fairness: the lock is acquired in the order in which thread are waiting for it, this can help prevent thread from being starved of resouces
     *
     *
     *
     *  thread pool
     *
     *  Executor vs ExecutorSevice vs Executors
     *  Executor: is used to execute a runnable task asynchronously on a new thread
     *  ExecutorService : manage the task: shutting down, submitting
     *
     *  Executors: to create a thread pool
     *   why should we have thread pool?
     *
     *   fixed - thread pool : initial value: how many threads you want to create
     *
     *   cached - you can create threads as much as you want, but a thread is idle for a specified time
     *   cached thread pool will terminate the thread.
     *
     *   can cached thread pool has a negative impact on the performance?
     *   yes: if we have too much threads are created and destroyed frequently, which can cause context switching overhead and increase the memory usage
     *
     *
     *   fork join pool:
     *
     *   suppose your task is huge-> you can split the huge task into the smaller task
     *   the smaller tasks can be executed in parallel.
     *
     *   work-stealing:
     *          when the thread completes its own task, it may "steal" tasks from the other thread,
     *          so that we can increase the performance of cpu.
     *
     *   single thread pool: just create a single thread.
     *
     *
     *  Spring: building the first restful api -> http(s) + tcp + upd
     *
     */
    //
    // shared lock vs exclusive lock in sql
    // synchronized + static : what are resources will be locked
    //CAS
    //thread safe collections in java
    // concurrentHashMap
    // blocking quueue
    //  pessimistic lock vs optimistic lock
    // reentrantlock
    //thread pool
    // fixed-szie
    // cached
    // single
    // fork join
private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
public void produder() throws InterruptedException{
    int value = 0;
    while(true){
        System.out.println("produce: " + value);
        queue.put(value++);
    }
}

public void consumer() throws InterruptedException{
    while (true){
        // when queue is full, consumer will take a value from queue
        if(queue.size() == 10){
            int value = queue.take();
            System.out.println("consumer: " + value);
            Thread.sleep(1000);
        }
    }
}
    public static void main(String[] args) throws InterruptedException {
        //producer - consumer model in blocking queue
        //
        Day5 producerConsumerExample = new Day5();
        new Thread(()->{
            try{
                producerConsumerExample.produder();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                producerConsumerExample.consumer();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

//        AtomicInteger atomicInteger = new AtomicInteger(0);// this is initial value is zero
//        int expectedValue = 0;
//        int newValue = 1;
//        System.out.println("the result is : " + atomicInteger.compareAndSet(expectedValue,newValue) + " get new value " + atomicInteger.get());
//
//
//        AtomicInteger atomicInteger1 = new AtomicInteger(0);//
//        int expectedValue1 = 1;
//        int newValue1 = 100;
//        System.out.println("the result is : " + atomicInteger1.compareAndSet(expectedValue1,newValue1) + " get new value " + atomicInteger1.get());

// ABA problem
//        AtomicInteger atomicInteger = new AtomicInteger(100);
//        new Thread(()->{
//            atomicInteger.compareAndSet(100, 101);
//            try{
//                TimeUnit.MICROSECONDS.sleep(10);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            atomicInteger.compareAndSet(101,100);
//        }).start();
//        new Thread(()->{
//            try{
//                TimeUnit.MICROSECONDS.sleep(200);
//                System.out.println("thread b is sleeping");
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println(atomicInteger.compareAndSet(100,200) + " " + atomicInteger.get());
//        }).start();


//        Day5 day5 = new Day5();
//        Day5 day51 = new Day5();
//
//        //create two threads here
//            new Thread(()->{
//            Day5.sendEmail();
//        },"Thread 1").start();
//
//            new Thread(()->{
//                day5.thisIsNonSyncMethod();
//        },"thread 2").start();

    }
    // Scenario 1 below: two thread with calling the same object
    // static: means some function/variable belongs to class itself.

    // assumning we have many/multiple synchronized methods in one object,
    // as long as one thread call one synchronizded method at certain time, other thread(s)
    // has(have) to wait the current thread to finish. without static keyword, synchronized keyword
    // will lock the current object: "this" object
    //  two thread with calling the diff objects
    //
    // in your main function
//    //create a new object
//    Day5 day5 = new Day5();
//
//    //create two threads here
//        new Thread(()->{
//        day5.sendEmail();
//    },"Thread 1").start();
//
//        new Thread(()->{
//        day5.sendSMS();
//    },"thread 2").start();
//    public synchronized void sendEmail(){

//        try{
//            //assume this are operations you need to do
//            TimeUnit.SECONDS.sleep(3);
//        }catch (InterruptedException exception){
//            exception.printStackTrace();
//        }
//        System.out.println("I am sending an email");
//        System.out.println("finished!");
//    }
//    public synchronized void  sendSMS(){
//        System.out.println("I am sending a sms");
//    }

    // Scenario 1 above

    //Scenario 2 below
    // when we call synchronized/ non synchronized method at the same time, there is no
    // resource competition happened
    // when we are using different object call  synchronized method at the same time, there is not
    // resource competition too.
//        public synchronized void sendEmail(){
//
//        try{
//            //assume this are operations you need to do
//            TimeUnit.SECONDS.sleep(3);
//        }catch (InterruptedException exception){
//            exception.printStackTrace();
//        }
//        System.out.println("I am sending an email");
//        System.out.println("finished!");
//    }
//    public synchronized void  sendSMS(){
//        System.out.println("I am sending a sms");
//    }
//    public void thisIsNonSyncMethod(){
//        System.out.println("I am a non sync method");
//    }
    // scenario 2 above
    // scenario 3 below
    // diff thread call synchronized  + static method at the same time, it will lock class itself,
    //one thread call synchronized + non static method, one thread call synchronized + static,
    // the first thread will not stopped,
    // for the non-static method, your locker will lock the current object: this
    // for the static method, your locker will lock the class itself.
//    public static synchronized void sendEmail(){
//
//        try{
//            //assume this are operations you need to do
//            TimeUnit.SECONDS.sleep(3);
//        }catch (InterruptedException exception){
//            exception.printStackTrace();
//        }
//        System.out.println("I am sending an email");
//        System.out.println("finished!");
//    }
//    public  static synchronized void  sendSMS(){
//        System.out.println("I am sending a sms");
//    }
//    public  void thisIsNonSyncMethod() {
//        System.out.println("I am a non sync method");
//    }

}
