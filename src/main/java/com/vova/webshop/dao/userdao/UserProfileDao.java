package com.vova.webshop.dao.userdao;

import com.vova.webshop.model.usermodel.UserProfile;

import java.util.List;

public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}