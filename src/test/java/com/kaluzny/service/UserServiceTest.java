package com.kaluzny.service;

import com.kaluzny.domain.User;
import com.kaluzny.domain.UserRepository;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

    @Inject
    private UserService userService;
    @Inject
    private UserRepository userRepository;
    @Inject
    private TestEntityManager testEntityManager;

    @Test
    public void contains_NotValidId_Success() {
        testEntityManager.merge(new User(1, "test title 1", 1));
        testEntityManager.merge(new User(2, "test title 2", 2));
        User isContains = userService.findUserById(3);
        assertEquals(null, isContains);
    }

    @Test
    public void contains_ValidId_Success() {
        User object = new User(1, "test title 1", 1);
        testEntityManager.merge(object);
        boolean isContains = userService.isUserExist(object);
        assertEquals(false, isContains);
    }

    @Test
    public void findAll_Success() {
        testEntityManager.merge(new User(1, "test title 1", 1));
        testEntityManager.merge(new User(2, "test title 2", 2));
        List<User> list = userService.findAllUsers();
        List<User> repositoryList = userRepository.findAll();
        assertArrayEquals(Arrays.array(repositoryList), Arrays.array(list));
    }

    @Test
    public void findAll_SuccessEmptyList() {
        List<User> list = userService.findAllUsers();
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void save_IdIsEqualsZero_Success() {
        User object = new User(0, "test title save", 1);
        userService.saveUser(object);
        Object lastIndex = testEntityManager.getId(object);
        User repositoryObject = testEntityManager.find(User.class, lastIndex);
        assertEquals("test title save", repositoryObject.getTitle());
        assertNotEquals(0, repositoryObject.getId());
    }

    @Test
    public void save_IdIsNotEqualsZero_Success() {
        User object = new User(2, "test title update", 1);
        testEntityManager.merge(object);
        int objectId = (int) object.getId();
        userService.saveUser(object);
        Object lastIndex = testEntityManager.getId(object);
        User repositoryObject = testEntityManager.find(User.class, lastIndex);
        assertEquals("test title update", repositoryObject.getTitle());
        assertNotEquals(0, repositoryObject.getId());
    }

    @Test
    public void findOne_NotValidId_Success() {
        User object = new User(1, "test title findOne", 5);
        testEntityManager.merge(object);
        int objectId = 100;
        User repositoryObject = userService.findUserById(objectId);
        assertNull(repositoryObject);
        assertNotEquals(object, repositoryObject);
    }
}
