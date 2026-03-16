package com.soen345.ticketreserve.repository;

import com.soen345.ticketreserve.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

        Optional<Event> findById(Long event_id);

        Optional<Event> findByTitle(String title);

        Optional<Event> findByCategory(String category);

        Optional<Event> findByLocation(String location);

        Optional<Event> findByEventDate(LocalDate eventDate);
        Event save(Event event);
        void deleteById(Long event_id);
}
