package com.soen345.ticketreserve.controller;

import com.soen345.ticketreserve.dto.LoginRequest;
import com.soen345.ticketreserve.dto.UserRegistrationRequest;
import com.soen345.ticketreserve.model.User;
import com.soen345.ticketreserve.service.UserService;
import com.soen345.ticketreserve.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldRegisterUser() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setName("Benjamin");
        request.setEmail("ben@test.com");
        request.setPassword("password123");

        User user = new User();
        user.setId(1L);
        user.setName("Benjamin");
        user.setEmail("ben@test.com");
        user.setRole("CUSTOMER");

        when(userService.registerUser(any())).thenReturn(user);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "name": "Benjamin",
              "email": "ben@test.com",
              "password": "password123"
            }
            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Benjamin"))
                .andExpect(jsonPath("$.email").value("ben@test.com"));
    }

    @Test
    void shouldLoginUser() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("ben@test.com");
        request.setPassword("password123");

        User user = new User();
        user.setId(1L);
        user.setName("Benjamin");
        user.setEmail("ben@test.com");
        user.setRole("CUSTOMER");

        when(userService.loginUser(any())).thenReturn(user);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "email": "ben@test.com",
              "password": "password123"
            }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ben@test.com"));
    }

    @Test
    void shouldReturnBadRequestWhenRegistrationFails() throws Exception {

        when(userService.registerUser(any()))
                .thenThrow(new BadRequestException("Email is already registered"));

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "name": "Benjamin",
                  "email": "ben@test.com",
                  "password": "password123"
                }
                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Email is already registered"));
    }

    @Test
    void shouldReturnBadRequestWhenLoginFails() throws Exception {

        when(userService.loginUser(any()))
                .thenThrow(new BadRequestException("Invalid credentials"));

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "email": "ben@test.com",
                  "password": "wrongpass"
                }
                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Invalid credentials"));
    }
}