package com.soen345.ticketreserve.repository;

import com.soen345.ticketreserve.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}