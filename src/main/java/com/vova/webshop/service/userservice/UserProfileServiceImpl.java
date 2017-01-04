package com.vova.webshop.service.userservice;

import com.vova.webshop.dao.userdao.UserProfileDao;
import com.vova.webshop.model.usermodel.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao dao;

    @Override
    public UserProfile findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<UserProfile> findAll() {
        return dao.findAll();
    }

    @Override
    public UserProfile findByType(String type) {
        return dao.findByType(type);
    }
}