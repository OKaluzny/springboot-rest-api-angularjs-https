package com.kaluzny.web;

import com.kaluzny.domain.User;
import com.kaluzny.service.UserService;
import com.kaluzny.service.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController("user")
@RequestMapping("/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* Create a User */
    @RequestMapping(
            value = "user",
            method = {RequestMethod.POST, RequestMethod.PUT})
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.saveUser(user);
    }

    @RequestMapping(
            value = "user/{id}",
            method = RequestMethod.DELETE)
    public String deleteUserFromDB(@PathVariable("id") Long id) {
        LOGGER.debug("INJECTED 'deleteUserFromDB' id: " + id);
        try {
            User user = new User(id);
            LOGGER.debug("CREATED 'deleteUserFromDB' user: " + user.toString());
            userService.deleteUserById(user);
        } catch (Exception exception) {
            return "Error deleting the user: " + exception.toString();
        }
        LOGGER.debug("<<< deleteUserFromDB... WITH result: User successfully deleted! New userSysStatus: \"-1\"");
        return "User successfully deleted!";
    }

    /* Retrieve All Users */
    @RequestMapping(
            value = "user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> listAllUsers() {
        LOGGER.debug("Received request to list all users");
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /* Retrieve Single User */
    @RequestMapping(
            value = "user/{id}",
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