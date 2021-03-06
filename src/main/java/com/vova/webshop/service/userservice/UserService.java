package com.vova.webshop.service.userservice;

import com.vova.webshop.model.itemmodel.Record;
import com.vova.webshop.model.usermodel.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

    void updateUserHistory(User user, Record historyList);
}