package com.kaluzny.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaluzny.domain.User;
import com.kaluzny.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Inject
    private MockMvc mockMvc;
    @Inject
    private WebApplicationContext context;
    @MockBean
    private UserService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .defaultRequest(get("/").with(user("user").password("user").roles("USER"))).build();
    }

    @Test
    public void findObjects_StorageIsNotEmpty_OneObjectIsReturned() throws Exception {
        given(service.findAllUsers()).willReturn(Arrays.asList(new User()));
        mockMvc
                .perform(get("/api/v1/objects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void saveUser_validUser_UserIsReturned() throws Exception {
        User user = new User();
        user.setValue(100);
        user.setTitle("Java 8");
        mockMvc
                .perform(post("/api/v1/objects")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MAPPER.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", Matchers.equalTo("Java 8")));

    }

    @Test
    public void saveUser_NotValidUser_BadRequest() throws Exception {
        User user = new User();
        user.setValue(15);
        mockMvc
                .perform(post("/api/v1/objects")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                //.content(MAPPER.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_validUser_UserIsReturned() throws Exception {
        User user = new User(2, "Java 8", 20);
        mockMvc
                .perform(post("/api/v1/objects")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MAPPER.writeValueAsString(user)));

        User user2 = new User(9, "Java 9", 22);
        given(service.findUserById(2)).willReturn(user2);
        mockMvc
                .perform(put("/api/v1/objects/2").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MAPPER.writeValueAsString(user2))).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", Matchers.equalTo("Java 9")));

    }

    @Test
    public void findUserByID_ValidId_OneObjectIsReturned() throws Exception {
        User user = new User(2, "Condenser", 100);
        given(service.findUserById(2)).willReturn(user);
        given(service.isUserExist(user)).willReturn(true);
        mockMvc
                .perform(get("/api/v1/objects/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)));
    }

    @Test
    public void findUserByID_NotValidId_NotFound() throws Exception {
        given(service.findUserById(1)).willReturn(null);
        mockMvc
                .perform(get("/api/v1/objects/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteObjectById_NotValidId_NotFound() throws Exception {
        User user = new User(10, "Java 9", 20);
        given(service.isUserExist(user)).willReturn(false);
        mockMvc
                .perform(delete("/api/v1/objects/10"))
                .andExpect(status().isNotFound());
    }
}
