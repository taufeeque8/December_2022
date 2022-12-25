package com.restfultutorial.restApi.controller;


import com.restfultutorial.restApi.db.DataBaseHandler;
import com.restfultutorial.restApi.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class HelloWorldController {
//    private DataBaseHandler dataBaseHandler;
    @GetMapping
    public List<User> getAllUse(){

        return DataBaseHandler.getAllUser();
    }
    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name){

        return DataBaseHandler.getUserByName(name);
    }

    @PostMapping
    public void createUser(@RequestBody User user){
         DataBaseHandler.addUser(user);
    }
    @DeleteMapping("/{name}")
    public void deleteUserByName(@PathVariable String name){

        DataBaseHandler.removeUser(name);
    }
}
