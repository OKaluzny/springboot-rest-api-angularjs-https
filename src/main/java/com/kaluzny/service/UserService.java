package com.kaluzny.service;

import com.kaluzny.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User getUserById(long id);

    List<User> getList();

    void delete(User user);
}