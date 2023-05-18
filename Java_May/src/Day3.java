import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.Optional;
public class Day3 {
    // new features in java 8
    //Agenda: what is functional interface, lambda function, stream api, optional, completable future, method reference
    // JVM: heap, stack , main memory , gc.

    /**
     *
     * collections                  vs               stream api
     * Collections: it is a data structure that store/ manipulate the date in MEMORY,
     * whereas stream api just process the data WITHOUT storing data in memory
     *
     * Collections can be modified by adding, removing elements,
     * Stream api: does not modify the original data but create a new steam with the result of the operation
     *
     * Collections: offers methods/functions for accessing and iterating all elements, whereas stream api
     * provides a set of function that can be applied to a stream of elements.
     *
     * parallel stream api:
     *  it will split the works into smaller streams that can be processed in parallel.
     *
     * optional:
     *  avoid any nullPointerException:
     *
     * completable future: a way to perform asynchronous computations
     *  asynchronous computations :
     *  ------ processing a large file / task ------- timeline-----
     *      Thread A        ---25% ----- 50%------75%-----100%-----
     *                           |
     *                          you call another thread B to send a notification to your users/ admins
     *
     *what is supplier
     *  it is a functional interface that has a single method called get(). get() method only return something, no take
     *  parameter
     * what is consumer
     *  it is also a functional interface that has a single method called accept(), accept() takes a single parameter
     *   not return something.
     *    public static CompletableFuture<Void> runAsync(Runnable runnable) {
     *         return asyncRunStage(asyncPool, runnable);
     *     }
     *       public static CompletableFuture<Void> runAsync(Runnable runnable,
     *                                                    Executor executor) {
     *         return asyncRunStage(screenExecutor(executor), runnable);
     *     }
     *
     *
     *      public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
     *         return asyncSupplyStage(asyncPool, supplier);
     *     }
     *
     *       public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,
     *                                                        Executor executor) {
     *         return asyncSupplyStage(screenExecutor(executor), supplier);
     *     }
     *
     *
     * get() vs join()
     *
     * public T join() {
     *         Object r;
     *         return reportJoin((r = result) == null ? waitingGet(false) : r);
     *     }
     *      public T get() throws InterruptedException, ExecutionException {
     *         Object r;
     *         return reportGet((r = result) == null ? waitingGet(true) : r);
     *     }
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {


         // how to use runAsync(runnale)
//        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
//                    System.out.println(Thread.currentThread().getName());
//                    try{
//                        TimeUnit.SECONDS.sleep(1);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//                );
//        System.out.println(completableFuture1.get());// fork join -
        // how to use runAsync(runnale, excutors)
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(()->{
//                    System.out.println(Thread.currentThread().getName());
//                    try{
//                        TimeUnit.SECONDS.sleep(1);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                },threadPool
//        );
//        System.out.println(completableFuture2.get()); // pool

        // spplyAsync without thread pool
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            try{
//                TimeUnit.SECONDS.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "task hello";
//        });
//        System.out.println(completableFuture.get());
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            try{
//                TimeUnit.SECONDS.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "task hello";
//        },threadPool);
//        System.out.println(completableFuture.get());
//        // missed shutdown
//        threadPool.shutdown();


       // suppose create three threads, each thread cost 500 millisecons
        // without future
//        long stratTime = System.currentTimeMillis();
//        //this is first thread and takes 500 millis second
//        try{
//            TimeUnit.MILLISECONDS.sleep(500);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        //this is second
//        try{
//            TimeUnit.MILLISECONDS.sleep(500);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        //this is last
//        try{
//            TimeUnit.MILLISECONDS.sleep(500);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        long endTime = System.currentTimeMillis();
//        // 1500 millis second
//        System.out.println("cost time :  " + (endTime  -stratTime));// > 1500
        // with future
        // this is creating thread pool,
        /// task 1 ------------
        ///task 2      --------------
        //task 3            --------------
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        long startTime  = System.currentTimeMillis();
//        //task 1 sleeps 500 millis second
//        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
//            try{
//                TimeUnit.MILLISECONDS.sleep(500);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "task1";
//        });
//        threadPool.submit(futureTask1);
//        //task 2 sleeps 500 millis second
//        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
//            try{
//                TimeUnit.MILLISECONDS.sleep(500);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "task2";
//        });
//        threadPool.submit(futureTask2);
//        //task 3 sleeps 500 millis second
//        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
//            try{
//                TimeUnit.MILLISECONDS.sleep(500);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "task3";
//        });
//        threadPool.submit(futureTask3);
//        try{
//            String re1 = futureTask1.get();
//            String re2 = futureTask2.get();
//            String re3 = futureTask3.get();
//
//        }catch (InterruptedException | ExecutionException exception){
//            exception.printStackTrace();
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println(" cost time:  " + (endTime  - startTime));// 500< < 1500
//        threadPool.shutdown();
//


//        int[] array = {1,2,3,4,5,6,7,8,9};
//        Arrays.stream(array).parallel().forEach(System.out::println);
//
//    Calculator adder = (a,b) ->(a+b);
//    int result = adder.calculate(1,2);
//        System.out.println(result);
//        // java 7
//        // times each integers by 2
//        List<Integer> l = Arrays.asList(1,2,3,4,5,6);
//        for(Integer i : l){
//            int myInt = i * 2;
//            System.out.println(myInt);
//        }
        // java 8
//        l.forEach(n->{
//            int x =  n * 2;
//        });
//        // you want to create a max heap
//        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
//        pq.add(1);
//        pq.add(2);
//        pq.forEach(System.out::println);
//
//        // you are sorting array list
//        List<Integer> list = Arrays.asList(2,4,3,6,1,9,8);
//        Collections.sort(list,(l1, l2) -> Integer.compare(l2,l1));
//        list.forEach(System.out::println);
//        // create a thread by using lambda
//        new Thread(()-> System.out.print("this is java 8")).start();
//

        // chaining function,
        // stream api is process the data from a collection, does not change the original data

        // in the stream api , we have two diff functions: intermediate function, terminal function
        // intermediate function:
            // filer(): selects only the elements that match a given condition,
            // map(): transforms the elements of the stream into a new type
            // sorted(): orders the elements based on a given condition
            // limit(): truncates the stream to a specified size
            // skip(n): skips the first n elements in the stream
        //  terminal function:
            // forEach(): performs an action on each elements
        // reduce(): aggregates the elements into a single result
        // collect(): return elements into a new collection -> list, arraylist
        // min()/ max(); find the min/ max value in the stream
        // count(): return the # of elements in the stream
        // anyMatch()/ allMatch() // nonMatch(): return a boolean, you will give  condition, return true/false, if there
        //any / all / none of elements match given a condition/predicate

        // examples with stream api
//        List<String> myStreamList = Arrays.asList("a1","b2","a3","c4","c6");
//        // find all the strings that start with "c"
//        // make it upper case
//        // and sort it
//        myStreamList.stream()
//                .filter(s->s.startsWith("c"))
//                .map(String::toUpperCase)
//                .sorted().forEach(System.out::println);
//        //I want to find any strings that contains lower case
//        List<String> myStreamList1 = Arrays.asList("a","SKKKKS","OOOO","cBBBB","caaaa");
//        myStreamList1.stream()
//                .filter(s -> s.chars().anyMatch(Character::isLowerCase))
//                .forEach(System.out::println);
//        // you 5 min: find any strings contains upper case
//        // return your result to a new arraylist
//        List<String> myRes = myStreamList1.
//                stream().filter(s -> s.chars().anyMatch(Character::isUpperCase))// find the upper case
//                .collect(Collectors.toList());
//
//        List<String> myStream2  = Arrays.asList("Abc123","abc123","abd","ABC123","abc123");
//        //find any string contains lowercase and numbers and return to a new array list
//        // you will two filters, use regex: ".*\\d.*"
//
//        List<String> res2 = myStream2.stream().filter( s -> s.matches(".*\\d.*"))
//                .filter(s -> s.chars().anyMatch(Character::isLowerCase)).collect(Collectors.toList());
//
//        // reduce
//        List<Integer> listNums = Arrays.asList(1,2,4,5,6,7,8,9,10);
//        // I want to sum of even numbers
//        int sumAll = listNums.stream()
//                .filter(n->n % 2 == 0)//filter out odd numbers
//                .reduce(0,Integer::sum);
//
//        System.out.println(sumAll);
        // your task is find the max number in above list
//        int maxNum = listNums.stream().reduce(Integer::max).get();
//
//        // any match, all match
//        // find any numbers >= 5
//        boolean exists = listNums.stream().anyMatch( n -> n >=5);
//
//        // find all numbers > 10
//        boolean findTen = listNums.stream().allMatch(n -> n > 3);
//        System.out.println(findTen);


//        BankAccount user1 = new BankAccount("12345", 1000.0, Optional.empty());
//        BankAccount user2 = new BankAccount("234556", 55.0, Optional.of("user2Password"));
//        //this is your subsystem that check each users have password
//        Optional<String> pw1 = user1.getPassword();
//        if(pw1.isPresent()){
//            String password2 = pw1.get();
//            System.out.println(password2);
//        }else{
//            System.out.println("user 1 does not have password");
//        }

    }




    // assume we are using java 7
    /**
     * new Thread(new Runnale(){
     * @Override
     *  public void run(){
     *      System.out.print("this is java 7")
     *  }
     *
     * })
     */
        // this is java 8 or new version
    //



}
 class BankAccount {
    private String accountNumber;
    private double balance;
    private Optional<String> password;

    public BankAccount(String accountNumber, double balance, Optional<String> password) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Optional<String> getPassword() {
        return password;
    }
}

