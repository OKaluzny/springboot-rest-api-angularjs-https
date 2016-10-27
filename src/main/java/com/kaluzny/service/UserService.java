package com.kaluzny.service;

import com.kaluzny.domain.User;

import java.util.List;

public interface UserService {

    public User save(User user);

    public User getUserById(long id);

    public List<User> getList();

    public void delete(User user);
}