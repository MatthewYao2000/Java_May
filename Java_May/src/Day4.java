import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Day4 {
    //Agenda: completable future, method reference, JVM, heap, stack, GC, threads
    private AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        byte[] allocation, allocation2;
                allocation =  new byte[30900*1024];
                // RUN -> CONFIGARATION -> MODIFY OIPTION -> ENVIRONMENT VARIABLE
                //-XX:+PrintGCDetails
        // -XX:+PrintGCTimeStamps
        // r = 1 -> 3
        // thenApply, thenRun, thenAccept
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            try{
//                TimeUnit.SECONDS.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            System.out.println("this is step 1");
//            return 1;
//        }, executorService).thenApply( r ->{
//            System.out.println("this is step 2");
//            return r + 1; // 1 -> 2
//        }).thenApply(r->{
//            System.out.println("this is step3");
//            return r + 1;
//        }).whenComplete((r,e) ->{
//            if(e == null){
//                System.out.println("whenComplete: we are finish, the result is: " + r );
//            }
//        }).exceptionally(e->{
//            e.printStackTrace();
//            return null;
//        });
//        System.out.println(Thread.currentThread().getName() + " is working");
//        executorService.shutdown();// always shut down
        // thenApply vs thenRun vs thenAccept
        // CompletableFuturn<Void>  == Void
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        // thenRun()-> to do task B when task A is finish, task B does not need the result of task A
//        System.out.println(CompletableFuture.supplyAsync(()->"this my result from then run, task A")
//                .thenRun(()->{/**this is task B*/}).join());
//        // thenAccept -> to do task B when task A is finish, B needs the result from A, not return value
//        System.out.println(CompletableFuture.supplyAsync(()->"this is my result from thenAccept, task A")
//                .thenAccept(r-> System.out.println(r)/**task B*/).join());
//        //thenApply -> to do task B when task A is finish, task B depends on task A, return result
//        System.out.println(CompletableFuture.supplyAsync(()->"this is my result from thenApply, task C")
//                .thenApply(r->r).join());
        //method reference
        // it is a way that allows us to pass behavior as a parameter
//        Function<String, Integer> parser = Integer ::parseInt;
//        // reference to static method
//        Integer res = parser.apply("123");
//        System.out.println(res.equals(123));
//        // new keyword
//        // List<Integer> list = new ArrayList<>();
//        //reference to a constructor
//        Supplier<List<String>> listSupplier = ArrayList::new;
//        List<String> myList = listSupplier.get();
//        myList.add("Matt");
//        myList.size();

        /// ***********threads***************
        // what are thread states we have in java
//       /** public enum State {
//            /**
//             * Thread state for a thread which has not yet started.
//             */
//            NEW,
//
//            /**
//             * Thread state for a runnable thread.  A thread in the runnable
//             * state is executing in the Java virtual machine but it may
//             * be waiting for other resources from the operating system
//             * such as processor.
//             */
//            RUNNABLE,
//
//            /**
//             * Thread state for a thread blocked waiting for a monitor lock.
//             * A thread in the blocked state is waiting for a monitor lock
//             * to enter a synchronized block/method or
//             * reenter a synchronized block/method after calling
//             * {@link Object#wait() Object.wait}.
//             */
//            BLOCKED,
//
//            /**
//             * Thread state for a waiting thread.
//             * A thread is in the waiting state due to calling one of the
//             * following methods:
//             * <ul>
//             *   <li>{@link Object#wait() Object.wait} with no timeout</li>
//             *   <li>{@link #join() Thread.join} with no timeout</li>
//             *   <li>{@link LockSupport#park() LockSupport.park}</li>
//             * </ul>
//             *
//             * <p>A thread in the waiting state is waiting for another thread to
//             * perform a particular action.
//             *
//             * For example, a thread that has called <tt>Object.wait()</tt>
//             * on an object is waiting for another thread to call
//             * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
//             * that object. A thread that has called <tt>Thread.join()</tt>
//             * is waiting for a specified thread to terminate.
//             */
//            WAITING,
//
//            /**
//             * Thread state for a waiting thread with a specified waiting time.
//             * A thread is in the timed waiting state due to calling one of
//             * the following methods with a specified positive waiting time:
//             * <ul>
//             *   <li>{@link #sleep Thread.sleep}</li>
//             *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
//             *   <li>{@link #join(long) Thread.join} with timeout</li>
//             *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
//             *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
//             * </ul>
//             */
//            TIMED_WAITING,
//
//            /**
//             * Thread state for a terminated thread.
//             * The thread has completed execution.
//             */
//            TERMINATED;
//        }
        // new Mythread(); -> new state, startz() -> runnable
        //runnable <-> blocked  you need to acquire a lock/ lock acquired
        //runnable -> terminated
        //runnable <-> time_waited, waiting for timeout notification,
        // runnable <-> waiting waiting for notification,

        // hint: native keyword -> some function is implemented by using c/C++
        //
        // do we have wait() function in the thread class?
        // what sleep()?
        // wait() vs sleep()
        // wait is a method defined in the Object class, whereas sleep method is a static method
        // lock: wait method will release the lock, sleep() method does noe release any locks,
        //                  the tread continues to hold the lock it acquired, if any.
        // exception: wait() to be called within a synchronized block. throws InterruptedException
        //              sleep(): does not need synchronized block, it also throws InterruptedException
        // wait(): notify() notifyAll() to wakeup
        // sleep() 1000 millis seconds, 1 second, does not need to notification, you need to specify some thime.
        //
        //
        Day4 day4 = new Day4();
        new Thread(day4::test).run();
        new Thread(day4::test).run();
//        1 12:08:21	Thread-0  gets into test(), thread 1 is waiting for release the resources
//        2 12:08:22	Thread-0 sleep 1 second, thread 1 not get in, sleep does not release the resource
//        3 12:08:22	Thread-1 thread 0 starts to wait, and thread 1 starts to sleep, wait() function release the resource
//        4 12:08:23	Thread-1 thread 1 is sleeping
//        5 12:08:25	Thread-0 starts to sleep 10s
//        6 12:08:35	Thread-0 wake up
//        7 12:08:35	Thread-1 starts to sleep 10s
//        8 12:08:45	Thread-1 wake up
        // https://hg.openjdk.org/jdk8u/jdk8u/jdk/file/f9ea6cf9425f/src/share/native/java/lang
        // run vs start():
        // start0() will create a new thread, whereas run function not create a new thread.
        // Mythread.start() -> Mythread.start() -> no
        // you can call run method as much as you want, but you can not call start method right after the one
        // run the code will be executed immediately
        // whereas start() function is not, it need to cpu resource avaible for the current thread
        //
    }
    private synchronized void test(){
        try{
            log("this is synchronized test(), start here to sleep 1 s");
            Thread.sleep(1000);
            log("wake with sleep function, wait() function is called, wait for 3 seconds");
            wait(3000);
            log("wake with wait() function, sleep for 10 s");
            Thread.sleep(10000);
            log("finished");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private void log(String s){
        System.out.println(count.incrementAndGet() + " "
        + new Date().toString().split(" ")[3]
        + "\t" + Thread.currentThread().getName());
    }
   // JVM, heap, stack, GC, threads
    /**
     * what is JVM?
     * class Main{
     *
     *     main{
     *
     *         system.out.print("this is jvm")
     *     }
     * }
     * Main.java file
     *        |
     *        |
     * Java compiler -> bytecode: Main.class file
     *
     * JVM[ class loader: will load your Main.class file into the some areas
     *       Execution engine: execute your Main.class file
     *       Runtime data area: it is memory, heap, stack, memory area -> provide memory/space
     *       to store, paramters, local variable, return values, bytecode, objects
     *       [
     *          runtime data area:
     *              method area: is used to store method data: method definitions, constants, static variables(shared)
     *
     *              stack: method invocation, local variables,  method arguments, return values(each thread has its own stack,
     *              which means, stack is not able to shared)
     *
     *              heap: Objects, GC in the heap(shared)
     *
     *       ]
     * ]
     *
     *  heap[
     *      MyClass myclass = new MyClass(); -> object stored in the heap
     *      byte[] myByte = new byte[999999999999999] -> this is huge object
     *      eden space: 99.99999% -> new object will be stored in the eden space, exception: when you create a huge object, the object
     *                              will be stored in the old generation
     *      survivor space: two spaces: S0, S1 -> survivor from, survivor to
     *            eden space + survivor space = young generation
     *
     *      old generation: 5 GC on oen object -> this object is 5 year old -> it is old
     *          when objects in the survivor space  have multiple GC, they are eventually moved to the old
     *          generation.
     *      metaspace(after java 8) ->stores class metadata, such as class definitions,
     *
     *  ]
     *  HOW to  JVM determine the object is alive or dead?
     *      GC roots:
     *          local variable, method parameters:
     *         static variables:
     *         thread stack:
     *         classes, class loader:
     *         ect....
     *         GC roots:
     *         /         \\\\
     *         obeject 1
     *         \ object2
     *
     *  GC
     *      stop-the-world(STW): the GC will stop your application
     *
     *    mark and swap
     *                      index 0      1  ...  n-1
     *    young generation space[[obj1][obj2]..[objn] [][]] before
     *                          [[obj1][    ][obj3] [   ].[objn] [][]] after
     *                          find the dead objects and remove it from young generation,
     *
     *                          suppose I would like to create an obejcts that is huge
     *          mark and swap with compacting
     *              [[obj1][obj2]..[objn] [][]] before
     *              [[obj1][    ][obj3] [   ].[objn] [][]] GC happens here
     *              [[obj1][obj3][objn] [] [] [] [] [] []]
     *  CMS
     *          minimize the pause time required for garbage collection
     *          which means will improve your application performance.
     *      concurrent mark and swap
     *              1: initial marking: STW happens during this
     *                        mark objects which are directly reachable from the GC roots
     *                        bitmap = [0,1] 1 means alive, 0 dead
     *                        [1,1,1,0,0,0]
     *                        after the initial marking phase, the application resumes execution
     *
     *              2: concurrent marking and swap: STW no happen here
     *                      this could happnes in parallel.
     *                      ## at the this phase, the CMS algorithm allows the application to create
     *                      ### a new object or some of objects could be dead during this phase
     *
     *              3: remark phase: STW happens here
     *                  it will mark all objects again
     *                  update bitmap during the second stage: second object is dead
     *                  [1,0,1,0,0,0]
     *
     *              4: final cleanup(final swap):
     *                  clean all dead objects
     *  G1: garbage first collection
     *      heap [ [ 76288K in young generation ]          ]
     *      heap [  [] [] [] [] [] [] [] [] [] [] [] [] [] [] ] -> 2048 segments in heap
     *      each region is marked as either young generation(further devied into edan or survivor) or old generation
     *
     *      steps:
     *          1 :initial mark
     *          2 : concurrent marking
     *          3:  remark
     *          4: cleanup
     *          5: compaction: make all used region together, all the empty region together
     *
     *
     *
     *
     *
     *
     *
     */

}

class MyThread extends  Thread{
    @Override
    public  void run(){
        System.out.println(getName());
    }
}
class MyRunnale implements Runnable{
    @Override
    public  void run(){
        System.out.println("this is my runnable");
    }
}
class MyCallable implements Callable<String> {
   public  String call() throws Exception{
       return "hello I am callable";
   }
}
