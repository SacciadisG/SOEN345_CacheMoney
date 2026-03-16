package com.soen345.ticketreserve.service;

import com.soen345.ticketreserve.model.Event;
import com.soen345.ticketreserve.repository.EventRepository;
import com.soen345.ticketreserve.exception.BadRequestException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Event not found with id: " + eventId));
    }

    public Event createEvent(Event event) {
        if (event.getTitle() == null || event.getTitle().trim().isEmpty()) {
            throw new BadRequestException("Event title is required");
        }
        if (event.getEventDate() == null) {
            throw new BadRequestException("Event date is required");
        }
        if (event.getLocation() == null || event.getLocation().trim().isEmpty()) {
            throw new BadRequestException("Event location is required");
        }
        if (event.getCategory() == null || event.getCategory().trim().isEmpty()) {
            event.setCategory("General");
        }
        if (event.getEventCapacity() <= 0) {
            throw new BadRequestException("Event capacity must be greater than 0");
        }
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new BadRequestException("Event not found with id: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }
}
