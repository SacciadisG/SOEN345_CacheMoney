package com.soen345.ticketreserve.controller;

import com.soen345.ticketreserve.dto.EventResponse;
import com.soen345.ticketreserve.dto.EventCreationRequest;
import com.soen345.ticketreserve.model.Event;
import com.soen345.ticketreserve.model.User;
import com.soen345.ticketreserve.service.EventService;
import com.soen345.ticketreserve.service.UserService;
import com.soen345.ticketreserve.exception.BadRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventCreationRequest request) {
        if (request.getOrganizerId() == null) {
            throw new BadRequestException("Organizer ID is required");
        }
        User organizer = userService.getUserById(request.getOrganizerId());
        Event event = new Event();
        event.setOrganizer(organizer);
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getDate());
        event.setLocation(request.getLocation());
        event.setCategory("General"); // Default category, can be modified to accept from request
        event.setEventCapacity(request.getEventCapacity());

        Event createdEvent = eventService.createEvent(event);

        EventResponse response = new EventResponse(
                createdEvent.getEventId(),
                createdEvent.getTitle(),
                createdEvent.getDescription(),
                createdEvent.getEventDate(),
                createdEvent.getLocation(),
                createdEvent.getEventCapacity()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
