package com.soen345.ticketreserve.controller;

import com.soen345.ticketreserve.exception.BadRequestException;
import com.soen345.ticketreserve.model.Event;
import com.soen345.ticketreserve.model.User;
import com.soen345.ticketreserve.service.EventService;
import com.soen345.ticketreserve.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventService eventService;

        @MockitoBean
        private UserService userService;

    @Test
    void shouldCreateEvent() throws Exception {
        Event created = new Event();
        created.setEventId(1L);
        created.setTitle("Spring Meetup");
        created.setDescription("Community event");
        created.setEventDate(LocalDate.of(2026, 4, 10));
        created.setLocation("Montreal");
        created.setCategory("General");
        created.setEventCapacity(120);

        User organizer = new User();
        organizer.setId(1L);

        when(userService.getUserById(1L)).thenReturn(organizer);
        when(eventService.createEvent(any(Event.class))).thenReturn(created);

        mockMvc.perform(post("/api/events/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                                                                        "organizerId": 1,
                  "title": "Spring Meetup",
                  "description": "Community event",
                  "date": "2026-04-10",
                  "location": "Montreal",
                  "eventCapacity": 120
                }
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventId").value(1))
                .andExpect(jsonPath("$.title").value("Spring Meetup"))
                .andExpect(jsonPath("$.description").value("Community event"))
                .andExpect(jsonPath("$.date").value("2026-04-10"))
                .andExpect(jsonPath("$.location").value("Montreal"))
                .andExpect(jsonPath("$.eventCapacity").value(120));
    }

    @Test
    void shouldReturnBadRequestWhenCreateFails() throws Exception {
                User organizer = new User();
                organizer.setId(1L);

                when(userService.getUserById(1L)).thenReturn(organizer);
        when(eventService.createEvent(any(Event.class)))
                .thenThrow(new BadRequestException("Event title is required"));

        mockMvc.perform(post("/api/events/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                                                                        "organizerId": 1,
                  "title": "",
                  "description": "Community event",
                  "date": "2026-04-10",
                  "location": "Montreal",
                  "eventCapacity": 120
                }
                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Event title is required"));
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        mockMvc.perform(delete("/api/events/delete/1"))
                .andExpect(status().isNoContent());

        verify(eventService).deleteEvent(1L);
    }

    @Test
    void shouldReturnBadRequestWhenDeleteFails() throws Exception {
        doThrow(new BadRequestException("Event not found with id: 999"))
                .when(eventService).deleteEvent(999L);

        mockMvc.perform(delete("/api/events/delete/999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Event not found with id: 999"));
    }
}
