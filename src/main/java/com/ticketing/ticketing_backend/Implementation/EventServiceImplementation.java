package com.ticketing.ticketing_backend.Implementation;
import com.ticketing.ticketing_backend.Model.Event;
import com.ticketing.ticketing_backend.Repository.EventRepository;
import com.ticketing.ticketing_backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEvent(Event event, MultipartFile imageFile) throws IOException {
        event.setImageName(imageFile.getOriginalFilename());
        event.setContentType(imageFile.getContentType());
        event.setImageData(imageFile.getBytes());
        return eventRepository.save(event);
    }
    public Event getEventById(String eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new RuntimeException("Event not found with id: " + eventId);
        }
    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public List<Event> getByEventName(String name) {
        return eventRepository.findByEventName(name);
    }
    @Override
    public List<Event> getByEventDate(LocalDate date) {
        return eventRepository.findByEventDate(date);
    }
    @Override
    public List<Event> getByEventVenue(String venue) {
        return eventRepository.findByEventVenue(venue);
    }
    public void updateEvent(String id, String eventName, String eventDate, String startTime, String endTime,
                            String eventVenue, Double oneTicketPrice, String description, MultipartFile image) throws IOException {

        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setEventName(eventName);
            event.setEventDate(eventDate);
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setEventVenue(eventVenue);
            event.setOneTicketPrice(oneTicketPrice);
            event.setDescription(description);

            if (image != null && !image.isEmpty()) {
                event.setImageData(image.getBytes());
                event.setContentType(image.getContentType());
            }

            eventRepository.save(event);
        } else {
            throw new IllegalArgumentException("Event not found with ID: " + id);
        }
    }
    @Override
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    @Override
    public boolean cancelEvent(String eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            Event canceledEvent = event.get();
            canceledEvent.setStatus("Cancelled"); // Assuming there's a 'status' field
            eventRepository.save(canceledEvent);
            return true;
        }
        return false;
    }

}
