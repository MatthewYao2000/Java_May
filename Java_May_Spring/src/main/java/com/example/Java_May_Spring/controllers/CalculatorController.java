package com.example.Java_May_Spring.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// to create our first restful api, you need restcontroller -> url
@RestController
public class CalculatorController {

    @GetMapping("/hello")// url -> localhost:8080/hello
    public String hello(){
        return "hello";
    }



}
