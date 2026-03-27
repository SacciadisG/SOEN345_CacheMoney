package com.soen345.ticketreserve.model;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void getEventsAttendingTest(){
        User user = new User();
        Event event1 = new Event();
        Event event2 = new Event();

        user.getEventsAttending().add(event1);
        user.getEventsAttending().add(event2);

        assert(user.getEventsAttending().size() == 2);
        assert(user.getEventsAttending().contains(event1));
        assert(user.getEventsAttending().contains(event2));
    }
}
