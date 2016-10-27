package com.kaluzny.controller;

import com.kaluzny.domain.User;
import com.kaluzny.service.UserService;
import com.kaluzny.service.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/user",
            method = {RequestMethod.POST, RequestMethod.PUT})
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.save(user);
    }

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.DELETE)
    public String deleteUserFromDB(@PathVariable("id") Long id) {
        LOGGER.debug("INJECTED 'deleteUserFromDB' id: " + id);
        try {
            User user = new User(id);
            LOGGER.debug("CREATED 'deleteUserFromDB' user: " + user.toString());
            userService.delete(user);
        } catch (Exception exception) {
            return "Error deleting the user: " + exception.toString();
        }
        LOGGER.debug("<<< deleteUserFromDB... WITH result: User successfully deleted! New userSysStatus: \"-1\"");
        return "User successfully deleted!";
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userService.getList();
    }

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") Long id) {
        LOGGER.debug("Received request by id user");
        return userService.getUserById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }
}