package com.soen345.ticketreserve.dto;

import java.time.LocalDate;

public class EventCreationRequest {
    private Long organizerId;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int eventCapacity;

    public EventCreationRequest() {}

    public Long getOrganizerId() {
        return organizerId;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getEventCapacity() {
        return eventCapacity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }
}
