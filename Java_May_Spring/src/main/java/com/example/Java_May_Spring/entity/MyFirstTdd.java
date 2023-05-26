package com.example.Java_May_Spring.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyFirstTdd {
    // you will find the first unique char in a give string,
    // input should always have a unique char
    //corner case:
    // throw IllegalArgumentException --> string = ""  and null
    //IllegalArgumentException -> case like this: AAaabb1122
    //upper case / lower case -> "Aa" -> A
    //


    public char firstUniqueCharacter(String str){
        if(str == null || str.equals("") ){
            throw new IllegalArgumentException("str is null or none");
        }
        Map<Character,Integer> cnt = new LinkedHashMap<>();
        for(int i = 0; i<str.length();i++){
            char ch = str.charAt(i);
            cnt.put(ch, cnt.getOrDefault(ch,0) + 1);
        }
        for(Map.Entry<Character,Integer> entry: cnt.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }

        throw new IllegalArgumentException("invalid input");

    }
}
