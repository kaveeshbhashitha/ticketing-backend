package com.ticketing.ticketing_backend.Service;
import com.ticketing.ticketing_backend.Model.Event;
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
    void deleteEvent(String id);
    public void updateEvent(String id, String eventName, String eventDate, String startTime, String endTime,
        String eventVenue, Double oneTicketPrice, String description, MultipartFile image) throws IOException;
    boolean cancelEvent(String eventId);
    }

