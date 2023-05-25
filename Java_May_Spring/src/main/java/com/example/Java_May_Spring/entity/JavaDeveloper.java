package com.example.Java_May_Spring.entity;

import org.springframework.stereotype.Component;

@Component("Java")
public class JavaDeveloper implements SoftwareEng{
    @Override
    public void sayHello() {
        System.out.println("I am Java");
    }
}
