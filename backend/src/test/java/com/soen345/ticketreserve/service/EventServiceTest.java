package com.soen345.ticketreserve.service;

import com.soen345.ticketreserve.exception.BadRequestException;
import com.soen345.ticketreserve.model.Event;
import com.soen345.ticketreserve.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventService = new EventService(eventRepository);
    }

    @Test
    void shouldCreateEventWhenValid() {
        Event event = validEvent();

        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event result = eventService.createEvent(event);

        assertEquals("Spring Meetup", result.getTitle());
        verify(eventRepository).save(event);
    }

    @Test
    void shouldThrowErrorWhenTitleMissing() {
        Event event = validEvent();
        event.setTitle("  ");

        assertThrows(BadRequestException.class, () -> eventService.createEvent(event));
    }

    @Test
    void shouldThrowErrorWhenDateMissing() {
        Event event = validEvent();
        event.setEventDate(null);

        assertThrows(BadRequestException.class, () -> eventService.createEvent(event));
    }

    @Test
    void shouldThrowErrorWhenLocationMissing() {
        Event event = validEvent();
        event.setLocation("");

        assertThrows(BadRequestException.class, () -> eventService.createEvent(event));
    }

    @Test
    void shouldThrowErrorWhenCategoryMissing() {
        Event event = validEvent();
        event.setCategory(null);

        assertThrows(BadRequestException.class, () -> eventService.createEvent(event));
    }

    @Test
    void shouldGetEventByIdWhenFound() {
        Event event = validEvent();
        event.setEventId(10L);

        when(eventRepository.findById(10L)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(10L);

        assertEquals(10L, result.getEventId());
        verify(eventRepository).findById(10L);
    }

    @Test
    void shouldThrowErrorWhenEventNotFoundById() {
        when(eventRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> eventService.getEventById(99L));
    }

    @Test
    void shouldDeleteEventWhenExists() {
        when(eventRepository.existsById(5L)).thenReturn(true);

        eventService.deleteEvent(5L);

        verify(eventRepository).deleteById(5L);
    }

    @Test
    void shouldThrowErrorWhenDeleteEventNotFound() {
        when(eventRepository.existsById(6L)).thenReturn(false);

        assertThrows(BadRequestException.class, () -> eventService.deleteEvent(6L));
    }

    private Event validEvent() {
        Event event = new Event();
        event.setTitle("Spring Meetup");
        event.setDescription("Community event");
        event.setEventDate(LocalDate.of(2026, 4, 10));
        event.setLocation("Montreal");
        event.setCategory("General");
        event.setEventCapacity(120);
        return event;
    }
}
