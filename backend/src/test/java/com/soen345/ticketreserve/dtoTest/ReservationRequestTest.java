package com.soen345.ticketreserve.dtoTest;

import com.soen345.ticketreserve.dto.ReservationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationRequestTest {

    @Test
    void shouldCreateReservationRequestAndUseGettersSetters() {
        ReservationRequest request = new ReservationRequest();

        request.setCustomerEmail("test@example.com");
        request.setEventName("Movie Night");
        request.setQuantity(2);

        assertEquals("test@example.com", request.getCustomerEmail());
        assertEquals("Movie Night", request.getEventName());
        assertEquals(2, request.getQuantity());
    }

    @Test
    void shouldCreateReservationRequestWithConstructor() {
        ReservationRequest request = new ReservationRequest("test@example.com", "Movie Night", 2);

        assertEquals("test@example.com", request.getCustomerEmail());
        assertEquals("Movie Night", request.getEventName());
        assertEquals(2, request.getQuantity());
    }
}