package com.restfultutorial.restApi.entity;

import lombok.ToString;

@ToString
public class User {
    private int age;
    private String name;
    private String role;
    public User(int age, String name, String role){
        this.age = age;
        this.name = name;
        this.role = role;
    }
    public String  getName(){
        return this.name;
    }
    public String getRole(){
        return  this.role;
    }

    public int getAge(){
        return  this.age;
    }
}
