package com.soen345.ticketreserve.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;
    private String eventName;
    private int quantity;

    public Reservation() {
    }

    public Reservation(String customerEmail, String eventName, int quantity) {
        this.customerEmail = customerEmail;
        this.eventName = eventName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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