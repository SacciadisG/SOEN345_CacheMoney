package com.soen345.ticketreserve.model;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testSettersAndGetters(){
        Event event = new Event();
        event.setTitle("Spring Meetup");
        event.setDescription("A meetup to discuss Spring Framework");
        event.setEventDate(java.time.LocalDate.of(2024, 10, 15));
        event.setLocation("Montreal");
        event.setEventCapacity(100);
        event.setStatus("Done");
        event.setCategory("Tech");

        assert(event.getTitle().equals("Spring Meetup"));
        assert(event.getDescription().equals("A meetup to discuss Spring Framework"));
        assert(event.getEventDate().equals(java.time.LocalDate.of(2024, 10, 15)));
        assert(event.getLocation().equals("Montreal"));
        assert(event.getEventCapacity() == 100);
        assert(event.getStatus().equals("Done"));
        assert(event.getCategory().equals("Tech"));
    }

    @Test
    void testConstructor(){
        Event event = new Event(
                "Spring Meetup",
                 java.time.LocalDate.of(2024, 10, 15),
                "Montreal",
                "Tech",
                100,
                "A meetup to discuss Spring Framework",
                "upcoming");

        assert(event.getTitle().equals("Spring Meetup"));
        assert(event.getDescription().equals("A meetup to discuss Spring Framework"));
        assert(event.getEventDate().equals(java.time.LocalDate.of(2024, 10, 15)));
        assert(event.getLocation().equals("Montreal"));
        assert(event.getEventCapacity() == 100);
        assert(event.getStatus().equals("upcoming"));
        assert(event.getCategory().equals("Tech"));
        assert(event.getOrganizer() == null);
    }
}
