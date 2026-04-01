package com.soen345.ticketreserve.service;

import com.soen345.ticketreserve.dto.ReservationRequest;
import com.soen345.ticketreserve.dto.ReservationResponse;
import com.soen345.ticketreserve.model.Reservation;
import com.soen345.ticketreserve.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationRepository reservationRepository;
    private EmailService emailService;
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        emailService = mock(EmailService.class);
        reservationService = new ReservationService(reservationRepository, emailService);
    }

    @Test
    void shouldCreateReservationAndSendEmail() {
        ReservationRequest request = new ReservationRequest(
                "test@example.com",
                "Movie Night",
                2
        );

        Reservation savedReservation = new Reservation(
                "test@example.com",
                "Movie Night",
                2
        );
        savedReservation.setId(1L);

        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        ReservationResponse response = reservationService.createReservation(request);

        assertNotNull(response);
        assertEquals(1L, response.getReservationId());
        assertEquals("test@example.com", response.getCustomerEmail());
        assertEquals("Movie Night", response.getEventName());
        assertEquals(2, response.getQuantity());

        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(emailService, times(1)).sendReservationConfirmation(
                "test@example.com",
                "Movie Night",
                2,
                1L
        );
    }
}