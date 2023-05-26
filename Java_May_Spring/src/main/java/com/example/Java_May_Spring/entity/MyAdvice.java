package com.example.Java_May_Spring.entity;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
//
public class MyAdvice {
    //pointcut is  you call getMapping,
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void myAdvicePointCut(){

    }

    @Before("myAdvicePointCut()")
    public void askPermission(){
        System.out.println("you need permission for a function");
    }

    @After("myAdvicePointCut()")
    public void closeConnection(){
        System.out.println("close connection");
    }
}
