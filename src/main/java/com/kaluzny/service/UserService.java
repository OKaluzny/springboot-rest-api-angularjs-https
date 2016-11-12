package com.kaluzny.service;

import com.kaluzny.domain.User;

import java.util.List;

public interface UserService {

    boolean isUserExist(User user);

    User saveUser(User user);

    User findUserById(long id);

    List<User> findAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);

    void deleteAllUsers();
}