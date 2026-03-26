package com.example.spareseat.model;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserResponseTest {

    private final Gson gson = new Gson();

    @Test
    public void allFields_deserializedCorrectly() {
        String json = "{\"id\":1,\"name\":\"Alice\",\"email\":\"alice@test.com\",\"phoneNumber\":\"5141234567\",\"role\":\"CUSTOMER\"}";
        UserResponse user = gson.fromJson(json, UserResponse.class);

        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("alice@test.com", user.getEmail());
        assertEquals("5141234567", user.getPhoneNumber());
        assertEquals("CUSTOMER", user.getRole());
    }

    @Test
    public void emailUser_phoneIsNull() {
        String json = "{\"id\":2,\"name\":\"Bob\",\"email\":\"bob@test.com\",\"phoneNumber\":null,\"role\":\"HOST\"}";
        UserResponse user = gson.fromJson(json, UserResponse.class);

        assertNotNull(user.getEmail());
        assertNull(user.getPhoneNumber());
        assertEquals("HOST", user.getRole());
    }

    @Test
    public void phoneUser_emailIsNull() {
        String json = "{\"id\":3,\"name\":\"Carol\",\"email\":null,\"phoneNumber\":\"5149876543\",\"role\":\"CUSTOMER\"}";
        UserResponse user = gson.fromJson(json, UserResponse.class);

        assertNull(user.getEmail());
        assertEquals("5149876543", user.getPhoneNumber());
    }

    @Test
    public void missingFields_defaultToNull() {
        String json = "{\"id\":4,\"name\":\"Dave\"}";
        UserResponse user = gson.fromJson(json, UserResponse.class);

        assertNull(user.getEmail());
        assertNull(user.getPhoneNumber());
        assertNull(user.getRole());
    }
}
