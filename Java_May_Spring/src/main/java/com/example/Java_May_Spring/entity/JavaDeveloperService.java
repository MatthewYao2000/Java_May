package com.example.Java_May_Spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JavaDeveloperService {
    @Autowired
    @Qualifier("React")
    SoftwareEng softwareEng;
    public void service(){
        softwareEng.sayHello();
    }
}
