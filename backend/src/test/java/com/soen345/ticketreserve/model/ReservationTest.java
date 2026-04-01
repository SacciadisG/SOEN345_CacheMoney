package com.soen345.ticketreserve.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {

    @Test
    void shouldCreateReservationAndUseGettersSetters() {
        Reservation reservation = new Reservation();

        reservation.setId(1L);
        reservation.setCustomerEmail("test@example.com");
        reservation.setEventName("Movie Night");
        reservation.setQuantity(2);

        assertEquals(1L, reservation.getId());
        assertEquals("test@example.com", reservation.getCustomerEmail());
        assertEquals("Movie Night", reservation.getEventName());
        assertEquals(2, reservation.getQuantity());
    }

    @Test
    void shouldCreateReservationWithConstructor() {
        Reservation reservation = new Reservation("test@example.com", "Movie Night", 2);

        assertEquals("test@example.com", reservation.getCustomerEmail());
        assertEquals("Movie Night", reservation.getEventName());
        assertEquals(2, reservation.getQuantity());
    }
}