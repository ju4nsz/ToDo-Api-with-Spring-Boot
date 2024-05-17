package com.ju4nsz.todoapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String fullName;

    @Column(length = 1, nullable = false)
    private String gender;

    @Column(length = 12, nullable = false, unique = true)
    private String phone_number;

    @Column(nullable = true)
    private int age;



}
