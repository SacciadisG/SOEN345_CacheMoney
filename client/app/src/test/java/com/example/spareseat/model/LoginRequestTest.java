package com.example.spareseat.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginRequestTest {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Test
    public void loginWithEmail_serializedCorrectly() {
        LoginRequest request = new LoginRequest("user@test.com", null, "password123");
        JsonObject json = toJson(request);

        assertEquals("user@test.com", json.get("email").getAsString());
        assertTrue(json.get("phoneNumber").isJsonNull());
        assertEquals("password123", json.get("password").getAsString());
    }

    @Test
    public void loginWithPhone_serializedCorrectly() {
        LoginRequest request = new LoginRequest(null, "5141234567", "password123");
        JsonObject json = toJson(request);

        assertTrue(json.get("email").isJsonNull());
        assertEquals("5141234567", json.get("phoneNumber").getAsString());
        assertEquals("password123", json.get("password").getAsString());
    }

    @Test
    public void serializedJson_containsExpectedKeys() {
        LoginRequest request = new LoginRequest("user@test.com", null, "password123");
        JsonObject json = toJson(request);

        assertTrue(json.has("email"));
        assertTrue(json.has("phoneNumber"));
        assertTrue(json.has("password"));
    }

    private JsonObject toJson(LoginRequest request) {
        return JsonParser.parseString(gson.toJson(request)).getAsJsonObject();
    }
}
