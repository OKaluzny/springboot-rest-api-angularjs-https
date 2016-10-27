package com.kaluzny.service;

import com.kaluzny.domain.User;
import com.kaluzny.repository.UserRepository;
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

@Service
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
    public User save(@NotNull @Valid User user) {
        LOGGER.debug("Creating {}", user);
        User existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        if (entityManager.contains(user))
            entityManager.remove(user);
        else
            entityManager.remove(entityManager.merge(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        LOGGER.debug(">>> getUserById for id =" + id);
        User user = repository.findOne(id);
        LOGGER.debug("user = " + user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        List<User> users = repository.findAll();
        LOGGER.debug("users = " + users);
        return users;
    }
}