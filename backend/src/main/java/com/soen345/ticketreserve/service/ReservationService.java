package com.soen345.ticketreserve.service;

import com.soen345.ticketreserve.dto.ReservationRequest;
import com.soen345.ticketreserve.dto.ReservationResponse;
import com.soen345.ticketreserve.model.Reservation;
import com.soen345.ticketreserve.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EmailService emailService;

    public ReservationService(ReservationRepository reservationRepository, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
    }

    public ReservationResponse createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation(
                request.getCustomerEmail(),
                request.getEventName(),
                request.getQuantity()
        );

        Reservation savedReservation = reservationRepository.save(reservation);

        emailService.sendReservationConfirmation(
                savedReservation.getCustomerEmail(),
                savedReservation.getEventName(),
                savedReservation.getQuantity(),
                savedReservation.getId()
        );

        return new ReservationResponse(
                savedReservation.getId(),
                "Reservation created successfully and email confirmation sent.",
                savedReservation.getCustomerEmail(),
                savedReservation.getEventName(),
                savedReservation.getQuantity()
        );
    }
}