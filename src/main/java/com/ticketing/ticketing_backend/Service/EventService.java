package com.ticketing.ticketing_backend.Service;
import com.ticketing.ticketing_backend.Model.Event;
import com.ticketing.ticketing_backend.Model.LoadFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface EventService {
    public Event addEvent(Event event, MultipartFile imageFile) throws IOException;
    Event getEventById(String id);
    List<Event> getAllEvents();
    List<Event> getByEventName(String name);
    List<Event> getByEventDate(LocalDate date);
    List<Event> getByEventVenue(String venue);
    void updateEvent(String id, Event updatedEvent);
    void deleteEvent(String id);
}
