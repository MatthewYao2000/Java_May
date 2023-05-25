package com.example.Java_May_Spring.entity;

import lombok.*;

@Setter // generate setters for you
@Getter // generate getters for you
@NoArgsConstructor // generate no params constructor for you
@AllArgsConstructor // generate params constructor
public class CalResult {
    private double result;
    public double getResult(){
        return result;
    }

    public  void setResult(double result){
        this.result = result;
    }
//    public CalResult(double result){
//        this.result = result;
//    }
    @Override
    public String toString() {
        return "CalResult{" +
                "result=" + result +
                '}';
    }
}
