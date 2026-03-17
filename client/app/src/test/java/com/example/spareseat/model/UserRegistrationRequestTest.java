package com.example.spareseat.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserRegistrationRequestTest {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Test
    public void registerWithEmail_serializedCorrectly() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Alice", "alice@test.com", null, "Password1", "CUSTOMER");
        JsonObject json = toJson(request);

        assertEquals("Alice", json.get("name").getAsString());
        assertEquals("alice@test.com", json.get("email").getAsString());
        assertTrue(json.get("phoneNumber").isJsonNull());
        assertEquals("Password1", json.get("password").getAsString());
        assertEquals("CUSTOMER", json.get("role").getAsString());
    }

    @Test
    public void registerWithPhone_serializedCorrectly() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Bob", null, "5141234567", "Password1", "HOST");
        JsonObject json = toJson(request);

        assertEquals("Bob", json.get("name").getAsString());
        assertTrue(json.get("email").isJsonNull());
        assertEquals("5141234567", json.get("phoneNumber").getAsString());
        assertEquals("HOST", json.get("role").getAsString());
    }

    @Test
    public void serializedJson_containsExpectedKeys() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Alice", "alice@test.com", null, "Password1", "CUSTOMER");
        JsonObject json = toJson(request);

        assertTrue(json.has("name"));
        assertTrue(json.has("email"));
        assertTrue(json.has("phoneNumber"));
        assertTrue(json.has("password"));
        assertTrue(json.has("role"));
    }

    @Test
    public void roleCustomer_serializedAsCustomer() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Alice", "alice@test.com", null, "Password1", "CUSTOMER");
        JsonObject json = toJson(request);

        assertEquals("CUSTOMER", json.get("role").getAsString());
    }

    @Test
    public void roleHost_serializedAsHost() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Bob", "bob@test.com", null, "Password1", "HOST");
        JsonObject json = toJson(request);

        assertEquals("HOST", json.get("role").getAsString());
    }

    private JsonObject toJson(UserRegistrationRequest request) {
        return JsonParser.parseString(gson.toJson(request)).getAsJsonObject();
    }
}
