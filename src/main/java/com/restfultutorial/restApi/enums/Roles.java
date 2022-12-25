package com.restfultutorial.restApi.enums;

import lombok.Getter;

@Getter
public enum Roles {
    STUDENT("STUDENT"),
    ADMIN("ADMIN");

    private final String role;

    Roles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
