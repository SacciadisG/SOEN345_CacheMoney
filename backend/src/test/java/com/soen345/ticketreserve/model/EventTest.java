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

        assert(event.getTitle().equals("Spring Meetup"));
        assert(event.getDescription().equals("A meetup to discuss Spring Framework"));
        assert(event.getEventDate().equals(java.time.LocalDate.of(2024, 10, 15)));
        assert(event.getLocation().equals("Montreal"));
        assert(event.getEventCapacity() == 100);
    }
}
