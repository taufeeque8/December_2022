package com.restfultutorial.restApi.db;



import com.restfultutorial.restApi.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler {
    public static List<User> users = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);

    }
    public static void removeUser(String name){
        for (User currUser: users) {
            if(currUser.getName().equals(name)) {
                users.remove(currUser);
                return;
            }
        }
    }

    public static List<User> getAllUser(){
        return  users;
    }

    public static User getUserByName(String name){
        for (User currUser: users) {
            if(currUser.getName().equals(name))
                return currUser;
        }
        return  null;
    }
}
