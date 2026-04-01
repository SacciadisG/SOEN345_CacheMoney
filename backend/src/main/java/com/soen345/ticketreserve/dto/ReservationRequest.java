package com.soen345.ticketreserve.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ReservationRequest {

    @Email(message = "Email must be valid")
    @NotBlank(message = "Customer email is required")
    private String customerEmail;

    @NotBlank(message = "Event name is required")
    private String eventName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public ReservationRequest() {
    }

    public ReservationRequest(String customerEmail, String eventName, int quantity) {
        this.customerEmail = customerEmail;
        this.eventName = eventName;
        this.quantity = quantity;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getEventName() {
        return eventName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}