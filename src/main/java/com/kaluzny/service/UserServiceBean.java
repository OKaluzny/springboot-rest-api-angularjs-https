package com.kaluzny.service;

import com.kaluzny.domain.User;
import com.kaluzny.domain.UserRepository;
import com.kaluzny.service.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service("userService")
@Validated
public class UserServiceBean implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceBean.class);

    @PersistenceContext
    private EntityManager entityManager;

    private UserRepository repository;

    @Inject
    public UserServiceBean(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User saveUser(@NotNull @Valid User user) {
        LOGGER.debug("Save {}", user);
        User existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(long id) {
        LOGGER.debug("Search by id: " + id);
        return repository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        LOGGER.debug("Retrieving the list of all users...");
        return repository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        LOGGER.debug("Updating... ", user);
        if (!entityManager.contains(user))
            user = entityManager.merge(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        LOGGER.debug("Deleting... ", user);
        if (entityManager.contains(user))
            entityManager.remove(user);
        else
            entityManager.remove(entityManager.merge(user));
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        LOGGER.debug("Removing the list of all users...");
        repository.deleteAll();
    }
}