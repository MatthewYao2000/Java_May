import java.util.List;
// oop, primitive type and wrapper type, java naming, string vs string builder vs string buffer
// interface vs abstract class, this keyword vs super keyword, overloading vs overriding
// static keyword. java pass by value or reference, shallow copy vs deep copy, exception,
public class Day1 {


    /**
     * what is object?
     *  state:
     *  behavior:
     * what is oop?
     *  polymorphism: "many forms"
     *      1: overloading vs overriding
     *          overloading: the same method name, but different variables: such as the numbers of variable
     *                       the types of variable
     *          overriding: it happens in child class, child class wants to inheritance some parent's functions/ feature
     *                      : code re-usability
     *      2: encapsulation: protects and manages its own information.
     *
                     class Student{
                     private int age;
                     public void setAge(int age){
                     this.age = age;
                     }
                     public int getAge(){
                     return this.age;
                     }
                     }
     *      3: abstraction: it is the process of hiding some details, bc you do not want to
     *                      show everything to your user
     *                      abstract class Cat{}
     *                      extends: to use abstract class
     *                  interface vs abstract class
     *                  question: can we have an abstract method in a non abstract class? No
     *                             can we have non-abstract method in an abstract class? yes
     *        4: inheritance:
     *
     *        Java naming principle:
     *          package:
     *              all letters should be lowercase
     *              :com.antra.src / com.antra.backend/ com.antra.java
     *          class:
     *              class test{}
     *              the first letter of the first word should be capitalized
     *              class Car{}/ class Student{}
     *          Interface: it is same with class
     *          filed and variable:
     *              the first letter of the first word should lower case,
     *              the first letter of the rest of words should be upper case
     *              myName / myInteger
     *          method: it is same with field and variable
     *          it would be good, if you use verb to name a method: eatFood()/ writeBook/ playVideoGame()
     *
     *          constant:
     *          all cases are upper case
     *          using underscore to separate each words
     *          public final int MY_BANK_ACCOUNT_BALANCE = 9999999;
     *
     *          primitive type and wrapper type: wrapper type offers more functions
     *          public static Integer valueOf(int i) {
     *          low = -128 high = 127
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
     *             }
     *
     *             String vs String builder vs String buffer
     *             string: is not changable bc final class, using char[] to store values
     *             string builder: changable
     *             string buffer synchronized
     *
     *    this keyword vs super keyword:
     *      this: 1: this can be used to reference current object
     *             2:this can be used to invoke/ call constructor
     *             3: can be used as parameter
     *             4: can we return this keyword in a method? yes
     *                   4.1: how can we build chaining function;
     *                          new Student.setName("matt").setAge(20).....
     *             5: this keyword can be used as constructor parameter
     *
     *      super:
     *              1: to invoke/call parent constructor
     *              2: to call parent function/ variable
     *              3: points/references to parent class
     *       doses java use pass by value or pass by reference? java always pass by value
     *          for a parameter of primitive type, the actual value is passed, changing the value of
     *          the local parameter inside the method does not change/effect the value of the variable
     *          outside the method.
     *
     *          for a parameter of reference type(arraylist, object). the value of the parameter contains
     *          a reference to an obejct(arraylist). the value of reference passed to the method.
     *          any changes will effect the origianl value of object(arraylist)
     *
     *
     *
     *          shallow copy vs deep copy
     *
     *
     *          static keyword
     *          if you used static to method, variable, block, it means the property belongs
     *          to class itself
     *          Student.myStaticMethod() -> we do not need to new an object
     *          non-static variable cannot be accessed within the static class
     *          constant value is often declared using static keyword
     *
     *          nonstatic
     *              it belongs to an object, you need create  a new object/ use new keyword
     *              Throwable
     *       extends/       \
     *         exception         Error
     *            /   \                \
     *       checked    unchecked         out of memory
     *
     *
     *       checked exception: happens in compile time. if we donot use try catch block, we will
     *       not able to run the code
     *       two ways to handle: throws keyword, try catch block classNotFound, IOException,sqlException
     *       uncheck: we do not need to use try catch block , it happens during run time
     *       indexOutOfBounds
     *
     *       throw vs throws
     *       throws: allow you to throw one or more exceptions
     *       throw: allow you to throw only one exception
     *
     *       throw
     *       Generic
     *       class generic, method generic, interface generic
     *       List<Integer>
     *
     *
     *
     *
     *
     */
    public class ThisAsConstructorParam{
        int count = 10;
        ThisAsConstructorParam(){
            Data data = new Data(this);
            data.out();// 10
        }
    }
    public class Data{
        ThisAsConstructorParam thisAsConstructorParam;
        Data(ThisAsConstructorParam thisAsConstructorParam){
            this.thisAsConstructorParam = thisAsConstructorParam;
        }
        void out(){
            System.out.println(thisAsConstructorParam.count);
        }
    }


    public void nonstaticMethod(){
        System.out.println("1");
    }
    public static void main(String[] args) throws Exception, RuntimeException, NumberFormatException {

//        String str1 = "Matt";
//        String str2 = "Yao";
//        String fullName = str1 + str2;// this called sugar syntax, java uses string builder behind this
//
//        // for(int i : intArray)
//        People people = new People().setAge(20).setLastName("Yao").setFirstName("Matt");
//
//       StringBuilder sb =new StringBuilder();
//        StringBuffer sb2 = new StringBuffer();
//            Integer myInteger1 = 12;
//            Integer myInteger2 = 12;
//            System.out.println(myInteger2 == myInteger1);
//
//            Integer myInteger3 = 200;
//            Integer myInteger4 = 200;
//            System.out.println(myInteger3 == myInteger4);
//
//            Integer i5 = new Integer(12);
//            System.out.println(myInteger1 == i5);
        // demo java pass by value
        MyCar car = new MyCar("BMW");
        System.out.println(car.getBrand());
        buyCar(car,"Toyota");
        System.out.println(car.getBrand());
        //demo checked expcetion
        if(car == null){
            throw  new Exception("car is null");
        }
        try{
        Class clazz = Class.forName("your.package.path");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void buyCar(MyCar car, String brand){
        car.setBrand(brand);
    }

}

// interface vs abstract class
abstract class AbClass{
    // constructor is allowed here
    // define a static and final string variable
    static final String myName = "";// 1
        int myInteger = 1;
    void thisIsMethod() {
        System.out.println("this is abstract class");
    }
    abstract void thisIsAbMethod();
}
interface InClass{
    // constrcutor is not allow here
    static final String myName = "";// 1
    void thisIsMethod();
    default void thisIsDefaultMethod(){
        System.out.println("default");
    }
    // after java 9 you are allow use private keyword, but you have to define a method body

}
class Student{
    private int age;
    private String myName;
    private String eMail;
    public Student(String myName, int age){
        this.myName  = myName; // if there is not this.myName = myName, the value of myName references to itself, not an object
        this.age = age;//
    }

    public Student() {

    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    void print(){
        System.out.println(myName + " " + age);
    }

    Student(String myName){
        System.out.println("only name");

    }
    public Student(String eMail, String myName, int age){
        this();
        System.out.println(this.eMail + this.myName + age);
    }
}
// test this keyword, can be used as parameter
class ThisAsParam{
    void method1(ThisAsParam thisAsParam){
        System.out.println( thisAsParam);
    }
    void method2(){
        method1(this);
    }
}
// test this return this keyword to build a chaining function
class People{
    private int age;
    private String firstName;
    private String lastName;
    //setters

    public People setAge(int age) {
        this.age = age;
        return this;
    }

    public People setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public People setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}

// pass by reference
class MyCar{
    private String brand;
    public MyCar(String brand){
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "MyCar{" +
                "brand='" + brand + '\'' +
                '}';
    }
}