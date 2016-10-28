package com.kaluzny.web;

import com.kaluzny.domain.User;
import com.kaluzny.service.UserService;
import com.kaluzny.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService);
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final User savedUser = stubServiceToReturnStoredUser();
        final User user = UserUtil.createUser();
        User returnedUser = userController.createUser(user);
        // verify user was passed to UserService
        verify(userService, times(1)).saveUser(user);
        assertEquals("Returned user should come from the service", savedUser, returnedUser);
    }

    private User stubServiceToReturnStoredUser() {
        final User user = UserUtil.createUser();
        when(userService.saveUser(any(User.class))).thenReturn(user);
        return user;
    }
/*
    @Test
    public void shouldListAllUsers() throws Exception {
        stubServiceToReturnExistingUsers(10);
        ResponseEntity<List<User>> users = userController.listAllUsers();
        assertNotNull(users);
        assertEquals(10, users.getBody());//.size());
        // verify user was passed to UserService
        verify(userService, times(1)).findAllUsers();//getList();
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userService.findAllUsers()).thenReturn(UserUtil.createUserList(howMany));
    }*/
}