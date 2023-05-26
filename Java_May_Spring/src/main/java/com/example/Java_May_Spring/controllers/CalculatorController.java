package com.example.Java_May_Spring.controllers;


import com.example.Java_May_Spring.entity.CalResult;
import com.example.Java_May_Spring.entity.Todo;
import com.example.Java_May_Spring.entity.Welcome;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// to create our first restful api, you need restcontroller -> url

//@Controller
@RestController
public class CalculatorController {

    @GetMapping("/todos")
    public ResponseEntity<Todo> todo(@Valid @RequestBody Todo todo){
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }
    @GetMapping("/welcome")
    public Welcome welcome(){
        return new Welcome("welcome object");
    }
    @GetMapping("/hello")// url -> localhost:8080/hello
    public String hello(){
        return "hello";
    }
    // requestBody -> object from user
    // requestParam used to fetch parameter from the URL
    // add
    @GetMapping("/add/{num1}/{num2}") // localhost:80/add/1/2
    public ResponseEntity<CalResult> add(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        //System.out.println("you need permission for a function");extract code to myAdvice class
        double res = num1 + num2;
        //System.out.println("close connection");extract code to myAdvice class
        return new ResponseEntity<>(new CalResult(res), HttpStatus.OK);

    }

    //sub
    @RequestMapping("/sub/{num1}/{num2}")
    public ResponseEntity<CalResult> sub(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        //System.out.println("you need permission for a function");extract code to myAdvice class
        double res = num1 - num2;
        //System.out.println("close connection");extract code to myAdvice class

        return new ResponseEntity<>(new CalResult(res), HttpStatus.OK);

    }

    @RequestMapping("/mul/{num1}/{num2}")
    public ResponseEntity<CalResult> mul(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        //System.out.println("you need permission for a function"); extract code to myAdvice class
        double res = num1 * num2;
        //System.out.println("close connection"); extract code to myAdvice class

        return new ResponseEntity<>(new CalResult(res), HttpStatus.OK);

    }
    @RequestMapping("/div/{num1}/{num2}")
    public ResponseEntity<CalResult> div(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        if(num2 == 0) throw  new ArithmeticException("num2 is zero");
        double res = num1 / num2;

        return new ResponseEntity<>(new CalResult(res), HttpStatus.OK);

    }



    @GetMapping("/test")
    public String test(@RequestBody CalResult calResult){
        return calResult.toString();
    }

    @GetMapping("/param")
    public String test(@RequestParam("myCalObject") String myCal){
        return myCal;
    }
}
