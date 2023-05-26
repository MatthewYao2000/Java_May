package com.example.Java_May_Spring.entity;
//spring validation

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private int id;
    @Size(min = 9, message = "the length should >= 9")
    private String desc;
    @NonNull
    private String userName;
}
