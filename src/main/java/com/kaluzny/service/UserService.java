package com.kaluzny.service;

import com.kaluzny.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public interface UserService {

    User saveUser(User user);

    User getUserById(long id);

    List<User> findAllUsers();

    void deleteUserById(User user);
}