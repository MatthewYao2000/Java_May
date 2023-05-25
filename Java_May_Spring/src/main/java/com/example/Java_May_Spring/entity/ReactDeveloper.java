package com.example.Java_May_Spring.entity;

import org.springframework.stereotype.Component;

@Component("React")
public class ReactDeveloper implements SoftwareEng{
    @Override
    public void sayHello() {
        System.out.println("I am react");
    }
}
