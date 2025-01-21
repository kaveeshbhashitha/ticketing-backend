package com.ticketing.ticketing_backend.Repository;
import com.ticketing.ticketing_backend.Model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, String> {
    @SuppressWarnings("null")
    Optional<Event> findById(String id);
    List<Event> findByEventDate(LocalDate date);
    List<Event> findByEventName(String name);
    List<Event> findByEventVenue(String venue);
}
