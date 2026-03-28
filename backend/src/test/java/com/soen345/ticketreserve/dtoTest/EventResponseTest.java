package com.soen345.ticketreserve.dtoTest;

import com.soen345.ticketreserve.dto.EventResponse;
import org.junit.jupiter.api.Test;

public class EventResponseTest {
    @Test
    void testSettersAndGetters(){
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(1L);
        eventResponse.setTitle("Spring Meetup");
        eventResponse.setDescription("A meetup to discuss Spring Framework");
        eventResponse.setDate(java.time.LocalDate.of(2024, 10, 15));
        eventResponse.setLocation("Montreal");
        eventResponse.setEventCapacity(100);

        assert(eventResponse.getEventId() == 1L);
        assert(eventResponse.getTitle().equals("Spring Meetup"));
        assert(eventResponse.getDescription().equals("A meetup to discuss Spring Framework"));
        assert(eventResponse.getDate().equals(java.time.LocalDate.of(2024, 10, 15)));
        assert(eventResponse.getLocation().equals("Montreal"));
        assert(eventResponse.getEventCapacity() == 100);
    }
}
