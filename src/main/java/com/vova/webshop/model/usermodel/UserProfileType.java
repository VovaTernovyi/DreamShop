package com.vova.webshop.model.usermodel;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    String userProfileType;

    UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}