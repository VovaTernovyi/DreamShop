package com.vova.webshop.service.userservice;

import com.vova.webshop.model.usermodel.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile findById(Integer id);

    List<UserProfile> findAll();

    UserProfile findByType(String type);
}