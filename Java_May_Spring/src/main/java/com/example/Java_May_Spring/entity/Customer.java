package com.example.Java_May_Spring.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    @Column(name = "age")
    private int age;
    @Column(name = "country")
    private String country;

}
